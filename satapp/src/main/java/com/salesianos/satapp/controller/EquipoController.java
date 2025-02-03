package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.GetEquipoDto;
import com.salesianos.satapp.dto.EditEquipoDto;
import com.salesianos.satapp.model.Equipo;
import com.salesianos.satapp.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/equipo/")
@RequiredArgsConstructor
@Tag(name = "Equipo", description = "El controlador para gestionar equipos")
public class EquipoController {

    private final EquipoService equipoService;

    @Operation(summary = "Obtener todos los equipos", description = "Devuelve una lista de todos los equipos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de equipos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = GetEquipoDto.class))))})
    @GetMapping
    public List<GetEquipoDto> findAll() {
        return equipoService.findAll().stream()
                .map(GetEquipoDto::of)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un equipo por su ID", description = "Devuelve los detalles de un equipo específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo encontrado",
                    content = @Content(schema = @Schema(implementation = GetEquipoDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity<GetEquipoDto> findById(@PathVariable Long id) {
        return equipoService.findById(id)
                .map(equipo -> ResponseEntity.ok(GetEquipoDto.of(equipo)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo equipo", description = "Crea un nuevo equipo con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipo creado",
                    content = @Content(schema = @Schema(implementation = GetEquipoDto.class)))})
    @PostMapping
    public ResponseEntity<GetEquipoDto> create(@RequestBody EditEquipoDto editEquipoDto) {
        Equipo equipo = new Equipo();
        equipo.setNombre(editEquipoDto.nombre());
        equipo.setCaracteristicas(editEquipoDto.caracteristicas());

        Equipo savedEquipo = equipoService.save(equipo);
        return ResponseEntity.status(201).body(GetEquipoDto.of(savedEquipo));
    }

    @Operation(summary = "Actualizar un equipo", description = "Actualiza los datos de un equipo existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo actualizado",
                    content = @Content(schema = @Schema(implementation = GetEquipoDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")})
    @PutMapping("/{id}")
    public ResponseEntity<GetEquipoDto> update(@PathVariable Long id, @RequestBody EditEquipoDto editEquipoDto) {
        Equipo equipoActualizado = new Equipo();
        equipoActualizado.setNombre(editEquipoDto.nombre());
        equipoActualizado.setCaracteristicas(editEquipoDto.caracteristicas());

        return ResponseEntity.ok(GetEquipoDto.of(equipoService.update(id, equipoActualizado)));
    }

    @Operation(summary = "Eliminar un equipo", description = "Elimina un equipo por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo eliminado"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
