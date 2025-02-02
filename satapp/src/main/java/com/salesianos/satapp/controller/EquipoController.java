package com.salesianos.satapp.controller;

import com.salesianos.satapp.model.Equipo;
import com.salesianos.satapp.service.EquipoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipo/")
@RequiredArgsConstructor
@Tag(name = "Equipo", description = "Controlador de equipo")
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping("/")
    public ResponseEntity<List<Equipo>> getAll() {
        List<Equipo> result = equipoService.findAll();

        if (result.isEmpty())

            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getById(@PathVariable Long id) {
        return ResponseEntity.of(equipoService.findById(id));
    }

}
