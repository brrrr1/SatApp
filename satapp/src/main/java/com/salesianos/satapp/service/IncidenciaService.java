package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.CreateIncidenciaDto;
import com.salesianos.satapp.model.Estado;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.repository.IncidenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .estado(incidenciaDto.estado().ABIERTA)
                .usuario(incidenciaDto.usuario())
                .categoria(incidenciaDto.categoria())
                .ubicacion(incidenciaDto.ubicacion())
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

    public Incidencia editIncidencia(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    public Incidencia cambiarEstado(Long id, Estado estado) {
        Incidencia incidencia = findById(id);
        incidencia.setEstado(estado);
        return incidenciaRepository.save(incidencia);
    }

}
