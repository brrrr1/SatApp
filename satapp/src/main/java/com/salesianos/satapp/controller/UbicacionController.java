package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.CreateUbicacionDto;
import com.salesianos.satapp.dto.GetUbicacionDto;
import com.salesianos.satapp.dto.EditEquipoDto;
import com.salesianos.satapp.model.Ubicacion;
import com.salesianos.satapp.service.UbicacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping("/ubicacion/")
@RequiredArgsConstructor
@Tag(name = "Ubicacion", description = "El controlador para gestionar ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @Operation(summary = "Obtener todas las ubicaciones", description = "Devuelve una lista de todas las ubicaciones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ubicaciones",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = GetUbicacionDto.class))))})
    @GetMapping
    public List<GetUbicacionDto> findAll() {
        return ubicacionService.findAll().stream()
                .map(GetUbicacionDto::of)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener una ubicación por su ID", description = "Devuelve los detalles de una ubicación específica por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ubicación encontrada",
                    content = @Content(schema = @Schema(implementation = GetUbicacionDto.class))),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada")})
    @GetMapping("/{id}")
    public ResponseEntity<GetUbicacionDto> findById(@PathVariable Long id) {
        return ubicacionService.findById(id)
                .map(ubicacion -> ResponseEntity.ok(GetUbicacionDto.of(ubicacion)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva ubicación", description = "Crea una nueva ubicación con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ubicación creada",
                    content = @Content(schema = @Schema(implementation = GetUbicacionDto.class)))})
    @PostMapping
    public ResponseEntity<GetUbicacionDto> create(@RequestBody CreateUbicacionDto createUbicacionDto) {

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setNombre(createUbicacionDto.nombre());


        Ubicacion savedUbicacion = ubicacionService.save(ubicacion);


        return ResponseEntity.status(201).body(GetUbicacionDto.of(savedUbicacion));
    }

    @Operation(summary = "Actualizar una ubicación", description = "Actualiza los datos de una ubicación existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ubicación actualizada",
                    content = @Content(schema = @Schema(implementation = GetUbicacionDto.class))),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada")})
    @PutMapping("/{id}")
    public ResponseEntity<GetUbicacionDto> update(@PathVariable Long id, @RequestBody Ubicacion ubicacionActualizada) {
        return ResponseEntity.ok(GetUbicacionDto.of(ubicacionService.update(id, ubicacionActualizada)));
    }

    @Operation(summary = "Eliminar una ubicación", description = "Elimina una ubicación por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ubicación eliminada"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ubicacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
