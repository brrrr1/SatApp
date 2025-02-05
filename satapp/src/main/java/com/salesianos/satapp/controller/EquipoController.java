package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.GetCategoriaDto;
import com.salesianos.satapp.dto.GetEquipoDto;
import com.salesianos.satapp.dto.EditEquipoDto;
import com.salesianos.satapp.dto.GetIncidenciaDto;
import com.salesianos.satapp.model.Categoria;
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
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Obtiene todos los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los equipos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetEquipoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Aire acondicionado",
                                                "caracteristicas": "Aire acondicionado de la sala de profesores",
                                                "ubicacion": {
                                                    "id": 1,
                                                    "nombre": "Sala de profesores",
                                                },
                                                "incidencias": [
                                                   
                                                ]
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el equipo",
                    content = @Content),
    })
    @GetMapping
    public List<GetEquipoDto> findAll() {
        return equipoService.findAll().stream()
                .map(GetEquipoDto::of)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtiene un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el equipo",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetEquipoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Aire acondicionado",
                                                "caracteristicas": "Aire acondicionado de la sala de profesores",
                                                "ubicacion": {
                                                    "id": 1,
                                                    "nombre": "Sala de profesores",
                                                },
                                                "incidencias": [
                                                   
                                                ]
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el equipo",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetEquipoDto> findById(@PathVariable Long id) {
        return equipoService.findById(id)
                .map(equipo -> ResponseEntity.ok(GetEquipoDto.of(equipo)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crea un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el equipo",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetEquipoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Aire acondicionado",
                                                "caracteristicas": "Aire acondicionado de la sala de profesores",
                                                "ubicacion": {
                                                    "id": 1,
                                                    "nombre": "Sala de profesores",
                                                },
                                                "incidencias": [
                                                   
                                                ]
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado el equipo",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetEquipoDto> create(@RequestBody EditEquipoDto editEquipoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetEquipoDto.of(equipoService.save(editEquipoDto)));
    }

    @Operation(summary = "Edita un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el equipo",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetEquipoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Aire acondicionado",
                                                "caracteristicas": "Aire acondicionado del aula 1",
                                                "ubicacion": {
                                                    "id": 2,
                                                    "nombre": "Aula 1",
                                                },
                                                "incidencias": [
                                                   
                                                ]
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha editado el equipo",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetEquipoDto> update(@PathVariable Long id, @RequestBody EditEquipoDto editEquipoDto) {
        return ResponseEntity.status(HttpStatus.OK).body(GetEquipoDto.of(equipoService.update(id, editEquipoDto)));
    }

    @Operation(summary = "Borra un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el equipo",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Equipo.class))
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido borrar el equipo",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
