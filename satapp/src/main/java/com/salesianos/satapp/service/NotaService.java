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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
                .fecha(LocalDate.from(notaNueva.fecha()))
                .contenido(notaNueva.contenido())
                .autor(notaNueva.autor())
                .incidencia(incidencia)
                .build();

        incidencia.addNota(nota);

        incidenciaRepository.save(incidencia);

        return nota;
    }

    public Nota update(Long notaId, CreateNotaDto notaNueva) {
        Optional<Nota> nota = incidenciaRepository.findByIdNota(notaId);

        if (nota.isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado una nota con ese id");
        }

        nota.get().setAutor(notaNueva.autor());
        nota.get().setFecha(LocalDate.from(notaNueva.fecha()));
        nota.get().setContenido(notaNueva.contenido());
        nota.get().setIncidencia(nota.get().getIncidencia());

        incidenciaRepository.save(nota.get().getIncidencia());

        return nota.get();
    }

}
