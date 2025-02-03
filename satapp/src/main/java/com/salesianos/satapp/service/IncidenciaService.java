package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.CreateIncidenciaDto;
import com.salesianos.satapp.model.Estado;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.repository.IncidenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;

    public Incidencia save(CreateIncidenciaDto incidenciaDto) {
        return incidenciaRepository.save(Incidencia.builder()
                .fecha(incidenciaDto.fecha().now())
                .titulo(incidenciaDto.titulo())
                .descripcion(incidenciaDto.descripcion())
                /*.estado(incidenciaDto.estado().ABIERTA)*/
                /*.usuario(incidenciaDto.usuario())
                .categoria(incidenciaDto.categoria())
                .ubicacion(incidenciaDto.ubicacion())*/
                .build());
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

    /*public Incidencia editIncidencia(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }*/

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
                .orElseThrow(() -> new EntityNotFoundException("No hay incidencia con ID: "+ id));

    }



    public Incidencia cambiarEstado(Long id, Estado estado) {
        Incidencia incidencia = findById(id);
        incidencia.setEstado(estado);
        return incidenciaRepository.save(incidencia);
    }


    public List<Incidencia> getIncidenciasByAlumno(Long alumnoId) {
        return incidenciaRepository.findByAlumnoId(alumnoId);
    }

    public List<Incidencia> getIncidenciasByFecha(LocalDateTime startDate, LocalDateTime endDate) {
        return incidenciaRepository.findByFechaCreacionBetween(startDate, endDate);
    }

    public List<Incidencia> getIncidenciasByUsuarioAndEstado(Long usuarioId, String estado) {
        return incidenciaRepository.findByUsuarioIdAndEstado(usuarioId, estado);
    }

}
