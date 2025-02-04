package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.CreateNotaDto;
import com.salesianos.satapp.dto.GetNotaDto;
import com.salesianos.satapp.model.Nota;
import com.salesianos.satapp.service.NotaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota/")
@RequiredArgsConstructor
@Tag(name = "Nota", description = "Controlador de notas")
public class NotaController {

    private final NotaService notaService;

    @GetMapping("/")
    public List<GetNotaDto> getAll() {
        return notaService.findAll().stream().map(GetNotaDto::of).toList();
    }

    @GetMapping("/{id}")
    public GetNotaDto getNotaById(@PathVariable Long id) {
        return GetNotaDto.of(notaService.findNotaById(id));
    }

    @PostMapping("/{incidenciaId}")
    public ResponseEntity<GetNotaDto> saveNota(@PathVariable Long incidenciaId, @RequestBody CreateNotaDto nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetNotaDto.of(notaService.saveNota(incidenciaId, nota)));
    }

    @PutMapping("/{notaId}/{incidenciaId}")
    public ResponseEntity<Nota> editNota(@PathVariable Long notaId, @RequestBody CreateNotaDto nota, @PathVariable Long incidenciaId) {
        return ResponseEntity.status(201).body(notaService.update(notaId, nota, incidenciaId));
    }

    @DeleteMapping("/{notaId}/{incidenciaId}")
    public ResponseEntity<?> deleteNota(@PathVariable Long notaId, @PathVariable Long incidenciaId) {
        notaService.deleteById(notaId);
        return ResponseEntity.noContent().build();
    }
}
