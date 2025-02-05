package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.CreateNotaDto;
import com.salesianos.satapp.dto.GetCategoriaDto;
import com.salesianos.satapp.dto.GetIncidenciaDto;
import com.salesianos.satapp.dto.GetNotaDto;
import com.salesianos.satapp.model.Nota;
import com.salesianos.satapp.service.NotaService;
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

@RestController
@RequestMapping("/nota/")
@RequiredArgsConstructor
@Tag(name = "Nota", description = "Controlador de notas")
public class NotaController {

    private final NotaService notaService;

    @Operation(summary = "Obtiene todas las notas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las notas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetNotaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "fecha": "2024-02-03T12:00:00",
                                                "contenido": "Se ha producido un error",
                                                "autor": "Juan",
                                                "incidencia": {
                                                    "id": 1
                                                }
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la nota",
                    content = @Content),
    })
    @GetMapping("/")
    public List<GetNotaDto> getAll() {
        return notaService.findAll().stream().map(GetNotaDto::of).toList();
    }

    @Operation(summary = "Obtiene una nota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la nota",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetNotaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "fecha": "2024-02-03T12:00:00",
                                                "contenido": "Se ha producido un error",
                                                "autor": "Juan",
                                                "incidencia": {
                                                    "id": 1
                                                }
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la nota",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetNotaDto getNotaById(@PathVariable Long id) {
        return GetNotaDto.of(notaService.findNotaById(id));
    }

    @Operation(summary = "Crea una nota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la nota",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetNotaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "fecha": "2024-02-03T12:00:00",
                                                "contenido": "Se ha producido un error",
                                                "autor": "Juan",
                                                "incidencia": {
                                                    "id": 1
                                                }
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la nota",
                    content = @Content),
    })
    @PostMapping("/{incidenciaId}")
    public ResponseEntity<GetNotaDto> saveNota(@PathVariable Long incidenciaId, @RequestBody CreateNotaDto nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetNotaDto.of(notaService.saveNota(incidenciaId, nota)));
    }

    @Operation(summary = "Edita una nota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la nota",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetNotaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "fecha": "2024-02-03T12:00:00",
                                                "contenido": "Se ha producido un error",
                                                "autor": "Manolo",
                                                "incidencia": {
                                                    "id": 1
                                                }
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha editado la nota",
                    content = @Content),
    })
    @PutMapping("/{notaId}")
    public GetNotaDto editNota(@PathVariable Long notaId, @RequestBody CreateNotaDto nota) {
        return GetNotaDto.of(notaService.update(notaId, nota));
    }

}
