package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.CreateIncidenciaDto;
import com.salesianos.satapp.dto.GetIncidenciaDto;
import com.salesianos.satapp.model.Estado;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.service.IncidenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/incidencia/")
@RequiredArgsConstructor
@Tag(name = "Incidencia", description = "El controlador de incidencias, para poder realizar todas las operaciones de gestión")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @Operation(summary = "Obtiene todas las incidencias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado incidencias",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 1,
                                                      "fecha": "29/01",
                                                      "titulo": "Aire acondicionado 2DAM",
                                                      "descripcion": "El aire acondicionado en la clase de 2DAM no va desde Octubre. Además, no hay mando.",
                                                      "estado": "ABIERTA",
                                                      "urgencia": "Muy urgente"
                                                      
                                                  },
                                                  {
                                                      "id": 2,
                                                      "fecha": "30/01",
                                                      "titulo": "Proyector 1AyF",
                                                      "descripcion": "El proyector en la clase de 1AyF ha dejado de funcionar.",
                                                      "estado": "ABIERTA",
                                                      "urgencia": "Para YA"
                                                      
                                                  }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna incidencia",
                    content = @Content),
    })
    @GetMapping
    public List<GetIncidenciaDto> getIncidencias() {
        return incidenciaService.findAll().stream().map(GetIncidenciaDto::of).toList();
    }

    @Operation(summary = "Obtiene una incidencia por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la incidencia",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                      "id": 1,
                                                      "fecha": "29/01",
                                                      "titulo": "Aire acondicionado 2DAM",
                                                      "descripcion": "El aire acondicionado en la clase de 2DAM no va desde Octubre. Además, no hay mando.",
                                                      "estado": "ABIERTA",
                                                      "urgencia": "Muy urgente"
                                                      
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la incidencia",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetIncidenciaDto getById(@PathVariable Long id) {
        return GetIncidenciaDto.of(incidenciaService.findById(id));
    }

    @Operation(summary = "Crea una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado la incidencia",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la incidencia",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<Incidencia> create (@RequestBody CreateIncidenciaDto incidencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaService.save(incidencia));
    }

    @Operation(summary = "Edita una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la incidencia",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Incidencia.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna incidencia",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public Incidencia editUser(@RequestBody Incidencia incidencia, @PathVariable Long id) {
        return incidenciaService.editIncidenciaUser(incidencia, id);
    }

    @Operation(summary = "Edita el estado de una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el estado de la incidencia",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Incidencia.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna incidencia",
                    content = @Content),
    })
    @PutMapping("/{id}/estado/")
    public GetIncidenciaDto cambiarEstado(@PathVariable Long id, Estado estado) {
        return GetIncidenciaDto.of(incidenciaService.cambiarEstado(id, estado));
    }


    @Operation(summary = "Borra una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado la incidencia",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Incidencia.class))
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido borrar la incidencia",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncidencia(@PathVariable Long id) {
        incidenciaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Obtiene las incidencias de un alumno por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las incidencias de ese usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 1,
                                                      "fecha": "29/01",
                                                      "titulo": "Aire acondicionado 2DAM",
                                                      "descripcion": "El aire acondicionado en la clase de 2DAM no va desde Octubre. Además, no hay mando.",
                                                      "estado": "Abierta",
                                                      "urgencia": "Muy urgente"
                                                      
                                                  },
                                                  {
                                                      "id": 2,
                                                      "fecha": "30/01",
                                                      "titulo": "Proyector 1AyF",
                                                      "descripcion": "El proyector en la clase de 1AyF ha dejado de funcionar.",
                                                      "estado": "Abierta",
                                                      "urgencia": "Para YA"
                                                      
                                                  }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado incidencias para ese usuario",
                    content = @Content),
    })
    @GetMapping("/{alumnoId}/incidencias")
    public List<GetIncidenciaDto> getIncidencias(@PathVariable Long alumnoId) {
        return incidenciaService.getIncidenciasByAlumno(alumnoId).stream().map(GetIncidenciaDto::of).toList();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las incidencias en ese rango de fechas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 1,
                                                      "fecha": "29/01",
                                                      "titulo": "Aire acondicionado 2DAM",
                                                      "descripcion": "El aire acondicionado en la clase de 2DAM no va desde Octubre. Además, no hay mando.",
                                                      "estado": "Abierta",
                                                      "urgencia": "Muy urgente"
                                                      
                                                  },
                                                  {
                                                      "id": 2,
                                                      "fecha": "30/01",
                                                      "titulo": "Proyector 1AyF",
                                                      "descripcion": "El proyector en la clase de 1AyF ha dejado de funcionar.",
                                                      "estado": "Abierta",
                                                      "urgencia": "Para YA"
                                                      
                                                  }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado incidencias en ese rango de fechas",
                    content = @Content),
    })
    @GetMapping("/filtrar")
    public ResponseEntity<List<GetIncidenciaDto>> getIncidenciasByFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(incidenciaService.getIncidenciasByFecha(startDate, endDate));
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las incidencias de ese técnico y por su estado",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 1,
                                                      "fecha": "29/01",
                                                      "titulo": "Aire acondicionado 2DAM",
                                                      "descripcion": "El aire acondicionado en la clase de 2DAM no va desde Octubre. Además, no hay mando.",
                                                      "estado": "Abierta",
                                                      "urgencia": "Muy urgente"
                                                      
                                                  },
                                                  {
                                                      "id": 2,
                                                      "fecha": "30/01",
                                                      "titulo": "Proyector 1AyF",
                                                      "descripcion": "El proyector en la clase de 1AyF ha dejado de funcionar.",
                                                      "estado": "Abierta",
                                                      "urgencia": "Para YA"
                                                      
                                                  }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado incidencias de ese técnico por su estado",
                    content = @Content),
    })
    @GetMapping("/usuario/{usuarioId}/estado")
    public ResponseEntity<List<Incidencia>> getIncidenciasByUsuarioAndEstado(@PathVariable Long usuarioId, @RequestParam String estado) {
        return ResponseEntity.ok(incidenciaService.getIncidenciasByUsuarioAndEstado(usuarioId, estado));
    }


}
