package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.CreateUbicacionDto;
import com.salesianos.satapp.model.Ubicacion;
import com.salesianos.satapp.repository.UbicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    public List<Ubicacion> findAll() {
        return ubicacionRepository.findAll();
    }

    public Optional<Ubicacion> findById(Long id) {
        return ubicacionRepository.findById(id);
    }

    public Ubicacion save(CreateUbicacionDto ubicacion) {
        return ubicacionRepository.save(Ubicacion.builder()
                .nombre(ubicacion.nombre())
                //.equipos(ubicacion.equipos())
                .build());
    }

    public void deleteById(Long id) {
        if (ubicacionRepository.existsById(id)) {
            ubicacionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Ubicación no encontrada");
        }
    }

    public Ubicacion update(Long id, Ubicacion ubicacionActualizada) {
        return ubicacionRepository.findById(id)
                .map(ubicacion -> {
                    ubicacion.setNombre(ubicacionActualizada.getNombre());
                    ubicacion.setEquipos(ubicacionActualizada.getEquipos());
                    return ubicacionRepository.save(ubicacion);
                })
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));
    }
}
