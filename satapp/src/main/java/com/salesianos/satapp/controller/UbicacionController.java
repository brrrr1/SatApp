package com.salesianos.satapp.controller;

import com.salesianos.satapp.model.Ubicacion;
import com.salesianos.satapp.service.UbicacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ubicacion/")
@RequiredArgsConstructor
@Tag(name = "Ubicacion", description = "Controlador de ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @GetMapping("/")
    public ResponseEntity<List<Ubicacion>> getAll() {
        List<Ubicacion> result = ubicacionService.findAll();

        if (result.isEmpty())

            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getById(@PathVariable Long id) {
        return ResponseEntity.of(ubicacionService.findById(id));
    }

}
