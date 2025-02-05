package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.*;
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
                                            [
                                                {
                                                    "incidenciaId": 51,
                                                    "fecha": "2024-02-03",
                                                    "contenido": "No funciona el botón del aire",
                                                    "autor": "Carlos Roman"
                                                },
                                                {
                                                    "incidenciaId": 101,
                                                    "fecha": "2024-02-03",
                                                    "contenido": "No enciende la pantalla del Pc 1",
                                                    "autor": "Carlos Roman"
                                                },
                                                {
                                                    "incidenciaId": 151,
                                                    "fecha": "2024-02-03",
                                                    "contenido": "No enciende la pantalla del Pc 2",
                                                    "autor": "Manuel Maman"
                                                },
                                                {
                                                    "incidenciaId": 201,
                                                    "fecha": "2024-02-03",
                                                    "contenido": "No enciende la pantalla del Pc 3",
                                                    "autor": "Carlos Ruiz"
                                                },
                                                {
                                                    "incidenciaId": 251,
                                                    "fecha": "2024-02-03",
                                                    "contenido": "No enciende la pantalla del Pc 4",
                                                    "autor": "Pablo Camara"
                                                },
                                                {
                                                    "incidenciaId": 301,
                                                    "fecha": "2024-02-03",
                                                    "contenido": "Pata rota de la mesa 1",
                                                    "autor": "Pedro Sanchez"
                                                },
                                                {
                                                    "incidenciaId": 351,
                                                    "fecha": "2024-02-03",
                                                    "contenido": "Pata rota de la mesa 2",
                                                    "autor": "Alvaro Castilla"
                                                },
                                                {
                                                    "incidenciaId": 401,
                                                    "fecha": "2024-02-03",
                                                    "contenido": "Pata rota de la mesa 3",
                                                    "autor": "Moisés Dorado"
                                                },
                                                {
                                                    "incidenciaId": 1,
                                                    "fecha": "2024-02-03",
                                                    "contenido": "Se ha roto por el lado más cercano a la puerta",
                                                    "autor": "Manolo Lama"
                                                },
                                                {
                                                    "incidenciaId": 1,
                                                    "fecha": "2024-02-03",
                                                    "contenido": "Se ha roto por el lado más cercano a la puerta",
                                                    "autor": "Mrs Margareth"
                                                }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la nota",
                    content = @Content),
    })
    @GetMapping
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
                                                "incidenciaId": 1,
                                                "fecha": "2024-02-03",
                                                "contenido": "Se ha roto por el lado más cercano a la puerta",
                                                "autor": "Manolo Lama"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la nota",
                    content = @Content),
    })
    @PostMapping("/{id}")
    public ResponseEntity<GetNotaDto> saveNota(@PathVariable Long id,
                                               @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                       description = "Cuerpo de la nota a crear", required = true,
                                                       content = @Content(mediaType = "application/json",
                                                               schema = @Schema(implementation = CreateNotaDto.class),
                                                               examples = @ExampleObject(value = """
    {
                                                                               "fecha": "2024-02-03T12:00:00",
                                                                               "contenido": "Se ha roto por el lado más cercano a la puerta",
                                                                               "autor": "Manolo Lama",
                                                                               "incidencia": {
                                                                                   "id": 1
                                                                               }
                                                                           }
""")))
                                               @RequestBody CreateNotaDto nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetNotaDto.of(notaService.saveNota(id, nota)));
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
                                                "incidenciaId": 1,
                                                "fecha": "2024-02-03",
                                                "contenido": "Se ha roto por el lado más cercano a la puerta",
                                                "autor": "Mrs Margareth"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha editado la nota",
                    content = @Content),
    })
    @PutMapping("/{notaId}")
    public GetNotaDto editNota(@PathVariable Long notaId,
                               @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                       description = "Cuerpo de la nota a editar", required = true,
                                       content = @Content(mediaType = "application/json",
                                               schema = @Schema(implementation = CreateNotaDto.class),
                                               examples = @ExampleObject(value = """
    {
                                                               "fecha": "2024-02-03T12:00:00",
                                                               "contenido": "Se ha roto por el lado más cercano a la puerta",
                                                               "autor": "Mrs Margareth",
                                                               "incidencia": {
                                                                   "id": 1
                                                               }
                                                           }
""")))
                               @RequestBody CreateNotaDto nota) {
        return GetNotaDto.of(notaService.update(notaId, nota));
    }

}
