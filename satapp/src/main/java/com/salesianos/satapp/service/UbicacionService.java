package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.GetUbicacionDto;
import com.salesianos.satapp.error.UbicacionNotFoundException;
import com.salesianos.satapp.model.Equipo;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.model.Ubicacion;
import com.salesianos.satapp.repository.EquipoRepository;
import com.salesianos.satapp.repository.IncidenciaRepository;
import com.salesianos.satapp.repository.UbicacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;
    private final IncidenciaRepository incidenciaRepository;
    private final EquipoRepository equipoRepository;

    public List<Ubicacion> findAll() {
        List<Ubicacion> ubicaciones = ubicacionRepository.findAll();

        if (ubicaciones.isEmpty()) {
            throw new UbicacionNotFoundException("Ubicación no encontrada");
        }
        return ubicaciones;
    }

    public Optional<Ubicacion> findById(Long ubicaId) {
        Optional<Ubicacion> ubicacion = ubicacionRepository.findById(ubicaId);
        if (ubicacion.isEmpty()) {
            throw new UbicacionNotFoundException("Ubicación no encontrada");
        }
        return ubicacion;
    }

    public Ubicacion saveUbicacion(GetUbicacionDto nuevo) {
        
        Ubicacion ubi = Ubicacion.builder()
                .nombre(nuevo.nombre())
                .build();

        return ubicacionRepository.save(ubi);
    }

    public void deleteUbicacion(Long idUbi) {

        Optional<Ubicacion> ubi = ubicacionRepository.findById(idUbi);

        if (ubi.isEmpty()) {
            throw new UbicacionNotFoundException("No se ha encontrado esta ubicación");
        }

        List<Incidencia> incidencias = incidenciaRepository.findByUbicacionId(idUbi);
        List<Equipo> equipos = equipoRepository.findByUbicacionId(idUbi);

        for (Incidencia incidencia : incidencias) {
            incidencia.setUbicacion(null);
            incidenciaRepository.save(incidencia);

        }

        for (Equipo equipo : equipos) {
            equipo.setUbicacion(null);
            equipoRepository.save(equipo);

        }
        ubicacionRepository.deleteById(idUbi);

    }

    public Ubicacion editUbicacion(Long id, GetUbicacionDto ubicacion) {
        Ubicacion ubi = ubicacionRepository.findById(id).orElseThrow(() -> new UbicacionNotFoundException(id));
        ubi.setNombre(ubicacion.nombre());
        return ubicacionRepository.save(ubi);
    }

}