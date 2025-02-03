package com.salesianos.satapp.service;

import com.salesianos.satapp.model.Equipo;
import com.salesianos.satapp.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> findById(Long id) {
        return equipoRepository.findById(id);
    }

    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public void deleteById(Long id) {
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Equipo no encontrado");
        }
    }

    public Equipo update(Long id, Equipo equipoActualizado) {
        return equipoRepository.findById(id)
                .map(equipo -> {
                    equipo.setNombre(equipoActualizado.getNombre());
                    equipo.setCaracteristicas(equipoActualizado.getCaracteristicas());
                    return equipoRepository.save(equipo);
                })
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }
}
