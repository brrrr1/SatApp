package com.salesianos.satapp.service;

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

    public Ubicacion save(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
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
                    return ubicacionRepository.save(ubicacion);
                })
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));
    }
}
