package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.GetIncidenciaDto;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.service.IncidenciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/incidencia/")
@RequiredArgsConstructor
@Tag(name = "Incidencia", description = "El controlador de incidencias, para poder realizar todas las operaciones de gestión")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @GetMapping
    public List<GetIncidenciaDto> getIncidencias() {
        return incidenciaService.findAll().stream().map(GetIncidenciaDto::of).toList();
    }

}
