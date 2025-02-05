package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.CreateIncidenciaDto;
import com.salesianos.satapp.dto.GetIncidenciaDto;
import com.salesianos.satapp.error.IncidenciaNotFoundException;
import com.salesianos.satapp.model.*;
import com.salesianos.satapp.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final UbicacionRepository ubicacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final EquipoRepository equipoRepository;

    public Incidencia save(CreateIncidenciaDto incidenciaDto) {

        Ubicacion ubicacion = incidenciaDto.ubicacion();
        if (ubicacion != null && (ubicacion.getId() == null || ubicacion.getId() == 0)) {
            ubicacion = ubicacionRepository.save(ubicacion);
        }

        Equipo equipo = incidenciaDto.equipo();
        if (equipo != null && (equipo.getId() == null || equipo.getId() == 0)) {
            equipo = equipoRepository.save(equipo);
        }

        Usuario usuario = incidenciaDto.usuario();
        if (usuario != null && usuario.getId() == 0) {
            usuario = usuarioRepository.save(usuario);
        }

        List<Categoria> categorias = new ArrayList<>();

        if (incidenciaDto.categoria() != null) {

            if (incidenciaDto.categoria() instanceof List) {
                for (Categoria c : (List<Categoria>) incidenciaDto.categoria()) {
                    if (c.getId() != null) {
                        Categoria categoriaExistente = categoriaRepository.findById(c.getId())
                                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + c.getId()));
                        categorias.add(categoriaExistente);
                    } else {
                        categorias.add(categoriaRepository.save(c));
                    }
                }
            } else {

                Categoria c = incidenciaDto.categoria();
                if (c.getId() != null) {
                    Categoria categoriaExistente = categoriaRepository.findById(c.getId())
                            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + c.getId()));
                    categorias.add(categoriaExistente);
                } else {
                    categorias.add(categoriaRepository.save(c));
                }
            }
        }

        Incidencia incidencia = Incidencia.builder()
                .fecha(incidenciaDto.fecha())
                .titulo(incidenciaDto.titulo())
                .descripcion(incidenciaDto.descripcion())
                .estado(incidenciaDto.estado())
                .urgencia(incidenciaDto.urgencia())
                .categoria(categorias.isEmpty() ? null : categorias.get(0))
                .equipo(equipo)
                .ubicacion(ubicacion)
                .usuario(usuario)
                .notas(new ArrayList<>())
                .build();

        List<Nota> notas = new ArrayList<>();
        if (incidenciaDto.notas() != null) {
            for (Nota n : incidenciaDto.notas()) {
                if (n != null) {
                    incidencia.addNota(n);
                    notas.add(n);
                }
            }
        }

        Incidencia incidenciaGuardada = Incidencia.builder()
                .fecha(incidencia.getFecha())
                .titulo(incidencia.getTitulo())
                .descripcion(incidencia.getDescripcion())
                .estado(incidencia.getEstado())
                .urgencia(incidencia.getUrgencia())
                .categoria(incidencia.getCategoria())
                .equipo(incidencia.getEquipo())
                .ubicacion(incidencia.getUbicacion())
                .usuario(incidencia.getUsuario())
                .notas(notas)
                .build();

        return incidenciaRepository.save(incidenciaGuardada);
    }

    public Incidencia findById(Long id) {
        return incidenciaRepository.findById(id).orElse(null);
    }

    public List<Incidencia> findAll() {
        return incidenciaRepository.findAll();
    }

    public void deleteById(Long id) {
        incidenciaRepository.deleteById(id);
    }

    public Incidencia editIncidencia(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    public Incidencia editIncidenciaUser(Incidencia incidencia, Long id) {
        return incidenciaRepository.findById(id)
                .map(old -> {
                    old.setTitulo(incidencia.getTitulo());
                    old.setDescripcion(incidencia.getDescripcion());
                    old.setEstado(incidencia.getEstado());
                    old.setCategoria(incidencia.getCategoria());
                    old.setEquipo(incidencia.getEquipo());
                    old.setUbicacion(incidencia.getUbicacion());


                    return incidenciaRepository.save(old);
                })
                .orElseThrow(() -> new IncidenciaNotFoundException("No se ha encontrado ninguna incidencia con ese id"));

    }



    /*public Incidencia cambiarEstado(Long id, Estado estado) {
        Incidencia incidencia = findById(id);
        incidencia.setEstado(estado);
        return incidenciaRepository.save(incidencia);
    }*/


    public List<Incidencia> getIncidenciasByAlumno(Long alumnoId) {
        return incidenciaRepository.findByAlumnoId(alumnoId);
    }

    public List<GetIncidenciaDto> getIncidenciasByFecha(LocalDateTime startDate, LocalDateTime endDate) {
        return incidenciaRepository.findByFechaBetween(startDate, endDate).stream().map(GetIncidenciaDto::of).toList();
    }

    /*public List<Incidencia> getIncidenciasByUsuarioAndEstado(Long usuarioId, String estado) {
        return incidenciaRepository.findByUsuarioIdAndEstado(usuarioId, estado);
    }*/

}
