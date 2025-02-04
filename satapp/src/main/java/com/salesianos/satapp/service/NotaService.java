package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.CreateNotaDto;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.model.Nota;
import com.salesianos.satapp.repository.IncidenciaRepository;
import com.salesianos.satapp.repository.NotaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotaService {

    private final IncidenciaRepository incidenciaRepository;

    public List<Nota> findAll() {

        List<Nota> notas = incidenciaRepository.findAllNotas();

        if(notas.isEmpty()) {
            throw new EntityNotFoundException("No se han encontrado notas");
        }

        return notas;

    }

    public Nota findNotaById(Long id) {
        return incidenciaRepository.findByIdNota(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la nota"));
    }

    public Nota saveNota(Long incidenciaId, CreateNotaDto notaNueva) {

        Incidencia incidencia = incidenciaRepository.findById(incidenciaId)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la incidencia"));

        Nota nota = Nota.builder()
                .fecha(java.time.LocalDateTime.now())
                .contenido(notaNueva.contenido())
                .autor(notaNueva.autor())
                .incidencia(incidencia)
                .build();

        incidencia.addNota(nota);

        incidenciaRepository.save(incidencia);

        return nota;
    }

    public Nota editNota(Long notaId, CreateNotaDto notaNueva) {

        Nota nota = incidenciaRepository.findByIdNota(notaId)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la nota"));

        nota.setAutor(notaNueva.autor());
        nota.setContenido(notaNueva.contenido());
        nota.setIncidencia(nota.getIncidencia());

        incidenciaRepository.save(nota.getIncidencia());

        return nota;
    }

    public void deleteById(Long id) {

        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la incidencia con id: " + id));

        incidencia.removeNota(findNotaById(id));
        incidenciaRepository.save(incidencia);
    }

}
