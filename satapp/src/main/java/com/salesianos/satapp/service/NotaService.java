package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.CreateCategoriaDto;
import com.salesianos.satapp.dto.CreateNotaDto;
import com.salesianos.satapp.model.Categoria;
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

    public Nota update(Long notaId, CreateNotaDto notaNueva, Long incidenciaId) {
            Incidencia incidencia = incidenciaRepository.findById(incidenciaId)
                    .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la incidencia"));

            Nota nota = findNotaById(notaId);

            nota.setContenido(notaNueva.contenido());
            nota.setAutor(notaNueva.autor());

            incidenciaRepository.save(incidencia);

            return nota;
    }

    public void deleteById(Long id) {
        Nota nota = findNotaById(id);
        Incidencia incidencia = nota.getIncidencia();
        incidencia.removeNota(nota);
        incidenciaRepository.save(incidencia);
    }

}
