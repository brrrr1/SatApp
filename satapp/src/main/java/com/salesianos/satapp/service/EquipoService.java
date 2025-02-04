package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.CreateCategoriaDto;
import com.salesianos.satapp.dto.EditEquipoDto;
import com.salesianos.satapp.model.Categoria;
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

    public Equipo save(EditEquipoDto editEquipoDto) {
        return equipoRepository.save(Equipo.builder()
                .nombre(editEquipoDto.nombre())
                .caracteristicas(editEquipoDto.caracteristicas())
                .ubicacion(editEquipoDto.ubicacion())
                .build());
    }

    public void deleteById(Long id) {
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Equipo no encontrado");
        }
    }

    public Equipo update(Long id, EditEquipoDto equipoActualizado) {
        return equipoRepository.findById(id)
                .map(equipo -> {
                    equipo.setNombre(equipoActualizado.nombre());
                    equipo.setCaracteristicas(equipoActualizado.caracteristicas());
                    equipo.setUbicacion(equipoActualizado.ubicacion());
                    return equipoRepository.save(equipo);
                })
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }
}
