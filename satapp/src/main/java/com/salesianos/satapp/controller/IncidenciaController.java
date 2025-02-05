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
                                    value = """
                                            {
                                                 "fecha": "2025-02-04T10:33:33",
                                                 "titulo": "Rotura del ordenador de Bruno Delgado",
                                                 "descripcion": "Es muy torpe y ha roto su ordenador",
                                                 "estado": "ABIERTA",
                                                 "urgencia": "Urgente",
                                                 "usuario": {
                                                     "id": 1,
                                                     "nombre": null,
                                                     "username": null,
                                                     "password": null,
                                                     "email": null,
                                                     "role": null
                                                 },
                                                 "categoria": {
                                                     "id": 251,
                                                     "nombre": "Impresoras",
                                                     "nombreCategoriaPadre": "InformÃ¡tica",
                                                     "listaCategoriasHijas": []
                                                 },
                                                 "equipo": {
                                                     "id": 1,
                                                     "nombre": "Pc Profesor",
                                                     "caracteristicas": "Rotura de pantalla"
                                                 },
                                                 "ubicacion": {
                                                     "id": 1,
                                                     "nombre": "Aula 1ºDAM"
                                                 },
                                                 "notas": []
                                             }
                                            """

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la incidencia",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetIncidenciaDto> create (
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Cuerpo de la incidencia a crear", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateIncidenciaDto.class),
                            examples = @ExampleObject(value = """
    {
                                                                                     "fecha": "2025-02-04T10:33:33",
                                                                                     "titulo": "Rotura del ordenador de Bruno Delgado",
                                                                                     "descripcion": "Es muy torpe y ha roto su ordenador",
                                                                                     "estado": "ABIERTA",
                                                                                     "urgencia": "Urgente",
                                                                                     "usuario": {
                                                                                         "id": 1
                                                                                     },
                                                                                     "categoria": {
                                                                                         "id": 251,
                                                                                         "nombre": "Impresoras",
                                                                                         "nombreCategoriaPadre": "Informática",
                                                                                         "listaCategoriasHijas": []
                                                                                     },
                                                                                     "equipo": {
                                                                                         "id": 1,
                                                                                         "nombre": "Pc Profesor",
                                                                                         "caracteristicas": "Rotura de pantalla"
                                                                                     },
                                                                                     "ubicacion": {
                                                                                         "id": 1,
                                                                                         "nombre": "Aula 1ºDAM"
                                                                                     },
                                                                                     "nota":{
                                                                                         "id": 1
                                                                                     }
                                                                                 }
                                    
""")))
            @RequestBody CreateIncidenciaDto incidencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaDto.of(incidenciaService.save(incidencia)));
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
    public Incidencia editUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Cuerpo de la incidencia a editar", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Incidencia.class),
                            examples = @ExampleObject(value = """
        {
                                                "fecha": "2025-02-04T10:33:33.062689",
                                                "titulo": "HOLA",
                                                "descripcion": "Descripción detallada de la incidencia",
                                                "estado": "ABIERTA",
                                                "urgencia": null,
                                                "usuario": null,
                                                "categoria": {
                                                    "id": 1,
                                                    "nombre": "Categoria 2",
                                                    "nombreCategoriaPadre": null,
                                                    "listaCategoriasHijas": []
                                                },
                                                "equipo": {
                                                    "id": 1,
                                                    "nombre": "manolo",
                                                    "caracteristicas": "boom"
                                                },
                                                "ubicacion": {
                                                    "id": 1,
                                                    "nombre": "salesianos"
                                                },
                                                "notas": [
                                                    {
                                                        "incidenciaId": 1,
                                                        "fecha": "2025-02-04T10:33:36.087819",
                                                        "contenido": "Ejemplo de contenido",
                                                        "autor": "Nombre del autor"
                                                    }
                                                ]
                                            }
""")))
            @RequestBody Incidencia incidencia, @PathVariable Long id) {
        return incidenciaService.editIncidenciaUser(incidencia, id);
    }

    /*@Operation(summary = "Edita el estado de una incidencia")
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
    }*/


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
                                                    "fecha": "2021-02-03T12:00:00",
                                                    "titulo": "Rotura pantalla profesor",
                                                    "descripcion": "Se ha roto la patalla del profesor, pinta feo",
                                                    "estado": "ABIERTA",
                                                    "urgencia": "Urgente",
                                                    "usuario": {
                                                        "id": 1,
                                                        "nombre": "Moisés Dorado",
                                                        "username": "moidor",
                                                        "password": "passwordmoidor",
                                                        "email": "moi.dor@gmail.com",
                                                        "role": "USER"
                                                    },
                                                    "categoria": {
                                                        "id": 251,
                                                        "nombre": "Impresoras",
                                                        "nombreCategoriaPadre": "Informática",
                                                        "listaCategoriasHijas": []
                                                    },
                                                    "equipo": {
                                                        "id": 1,
                                                        "nombre": "Pc Profesor",
                                                        "caracteristicas": "Rotura de pantalla"
                                                    },
                                                    "ubicacion": {
                                                        "id": 1,
                                                        "nombre": "Aula 1ºDAM"
                                                    },
                                                    "notas": [
                                                        {
                                                            "incidenciaId": 1,
                                                            "fecha": "2024-02-03",
                                                            "contenido": "Se ha roto la pantalla del profesor, pinta feo",
                                                            "autor": "Moisés Dorado"
                                                        }
                                                    ]
                                                },
                                                {
                                                    "fecha": "2024-02-03T12:00:00",
                                                    "titulo": "Pata rota",
                                                    "descripcion": "Pata rota de la mesa 3",
                                                    "estado": "ABIERTA",
                                                    "urgencia": "Urgente",
                                                    "usuario": {
                                                        "id": 1,
                                                        "nombre": "Moisés Dorado",
                                                        "username": "moidor",
                                                        "password": "passwordmoidor",
                                                        "email": "moi.dor@gmail.com",
                                                        "role": "USER"
                                                    },
                                                    "categoria": {
                                                        "id": 251,
                                                        "nombre": "Impresoras",
                                                        "nombreCategoriaPadre": "Informática",
                                                        "listaCategoriasHijas": []
                                                    },
                                                    "equipo": {
                                                        "id": 401,
                                                        "nombre": "Mesa 3",
                                                        "caracteristicas": "Pata rota"
                                                    },
                                                    "ubicacion": {
                                                        "id": 151,
                                                        "nombre": "Aula 2ºAyF"
                                                    },
                                                    "notas": [
                                                        {
                                                            "incidenciaId": 401,
                                                            "fecha": "2024-02-03",
                                                            "contenido": "Pata rota de la mesa 3",
                                                            "autor": "Moisés Dorado"
                                                        }
                                                    ]
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
                                                     "fecha": "2024-02-03T12:00:00",
                                                     "titulo": "Rotura pantalla profesor",
                                                     "descripcion": "Se ha roto la patalla del profesor, pinta feo",
                                                     "estado": "ABIERTA",
                                                     "urgencia": "Urgente",
                                                     "usuario": {
                                                         "id": 1,
                                                         "nombre": "Moisés Dorado",
                                                         "username": "moidor",
                                                         "password": "passwordmoidor",
                                                         "email": "moi.dor@gmail.com",
                                                         "role": "USER"
                                                     },
                                                     "categoria": {
                                                         "id": 251,
                                                         "nombre": "Impresoras",
                                                         "nombreCategoriaPadre": "Informática",
                                                         "listaCategoriasHijas": []
                                                     },
                                                     "equipo": {
                                                         "id": 1,
                                                         "nombre": "Pc Profesor",
                                                         "caracteristicas": "Rotura de pantalla"
                                                     },
                                                     "ubicacion": {
                                                         "id": 1,
                                                         "nombre": "Aula 1ºDAM"
                                                     },
                                                     "notas": [
                                                         {
                                                             "incidenciaId": 1,
                                                             "fecha": "2024-02-03",
                                                             "contenido": "Se ha roto la pantalla del profesor, pinta feo",
                                                             "autor": "Moisés Dorado"
                                                         }
                                                     ]
                                                 },
                                                 {
                                                     "fecha": "2024-02-03T12:00:00",
                                                     "titulo": "Aire acondionado estropeado",
                                                     "descripcion": "No funciona el botón del aire",
                                                     "estado": "ABIERTA",
                                                     "urgencia": "Urgente",
                                                     "usuario": {
                                                         "id": 101,
                                                         "nombre": "Carlos Roman",
                                                         "username": "carrom",
                                                         "password": "passwordcarrom",
                                                         "email": "carlos.roman@gmail.com",
                                                         "role": "USER"
                                                     },
                                                     "categoria": {
                                                         "id": 251,
                                                         "nombre": "Impresoras",
                                                         "nombreCategoriaPadre": "Informática",
                                                         "listaCategoriasHijas": []
                                                     },
                                                     "equipo": {
                                                         "id": 51,
                                                         "nombre": "Aire Acondicionado",
                                                         "caracteristicas": "No funciona el botón de encendido"
                                                     },
                                                     "ubicacion": {
                                                         "id": 51,
                                                         "nombre": "Aula 2ºDAM"
                                                     },
                                                     "notas": [
                                                         {
                                                             "incidenciaId": 51,
                                                             "fecha": "2024-02-03",
                                                             "contenido": "No funciona el botón del aire",
                                                             "autor": "Carlos Roman"
                                                         }
                                                     ]
                                                 },
                                                 {
                                                     "fecha": "2024-02-03T12:00:00",
                                                     "titulo": "No enciende la pantalla",
                                                     "descripcion": "No enciende la pantalla del Pc 1",
                                                     "estado": "ABIERTA",
                                                     "urgencia": "Urgente",
                                                     "usuario": {
                                                         "id": 101,
                                                         "nombre": "Carlos Roman",
                                                         "username": "carrom",
                                                         "password": "passwordcarrom",
                                                         "email": "carlos.roman@gmail.com",
                                                         "role": "USER"
                                                     },
                                                     "categoria": {
                                                         "id": 251,
                                                         "nombre": "Impresoras",
                                                         "nombreCategoriaPadre": "Informática",
                                                         "listaCategoriasHijas": []
                                                     },
                                                     "equipo": {
                                                         "id": 101,
                                                         "nombre": "Pc 1",
                                                         "caracteristicas": "No enciende la pantalla"
                                                     },
                                                     "ubicacion": {
                                                         "id": 101,
                                                         "nombre": "Aula 1ºAyF"
                                                     },
                                                     "notas": [
                                                         {
                                                             "incidenciaId": 101,
                                                             "fecha": "2024-02-03",
                                                             "contenido": "No enciende la pantalla del Pc 1",
                                                             "autor": "Carlos Roman"
                                                         }
                                                     ]
                                                 },
                                                 {
                                                     "fecha": "2024-02-03T12:00:00",
                                                     "titulo": "No enciende la pantalla",
                                                     "descripcion": "No enciende la pantalla del Pc 2",
                                                     "estado": "ABIERTA",
                                                     "urgencia": "Urgente",
                                                     "usuario": {
                                                         "id": 201,
                                                         "nombre": "Manuel Maman",
                                                         "username": "manmam",
                                                         "password": "passwordmanmam",
                                                         "email": "manuel.maman@gmail.com",
                                                         "role": "USER"
                                                     },
                                                     "categoria": {
                                                         "id": 251,
                                                         "nombre": "Impresoras",
                                                         "nombreCategoriaPadre": "Informática",
                                                         "listaCategoriasHijas": []
                                                     },
                                                     "equipo": {
                                                         "id": 151,
                                                         "nombre": "Pc 2",
                                                         "caracteristicas": "No enciende la pantalla"
                                                     },
                                                     "ubicacion": {
                                                         "id": 101,
                                                         "nombre": "Aula 1ºAyF"
                                                     },
                                                     "notas": [
                                                         {
                                                             "incidenciaId": 151,
                                                             "fecha": "2024-02-03",
                                                             "contenido": "No enciende la pantalla del Pc 2",
                                                             "autor": "Manuel Maman"
                                                         }
                                                     ]
                                                 },
                                                 {
                                                     "fecha": "2024-02-03T12:00:00",
                                                     "titulo": "No enciende la pantalla",
                                                     "descripcion": "No enciende la pantalla del Pc 3",
                                                     "estado": "ABIERTA",
                                                     "urgencia": "Urgente",
                                                     "usuario": {
                                                         "id": 301,
                                                         "nombre": "Carlos Ruiz",
                                                         "username": "carrui",
                                                         "password": "passwordcarrui",
                                                         "email": "carlos.ruiz@gmail.com",
                                                         "role": "USER"
                                                     },
                                                     "categoria": {
                                                         "id": 251,
                                                         "nombre": "Impresoras",
                                                         "nombreCategoriaPadre": "Informática",
                                                         "listaCategoriasHijas": []
                                                     },
                                                     "equipo": {
                                                         "id": 201,
                                                         "nombre": "Pc 3",
                                                         "caracteristicas": "No enciende la pantalla"
                                                     },
                                                     "ubicacion": {
                                                         "id": 101,
                                                         "nombre": "Aula 1ºAyF"
                                                     },
                                                     "notas": [
                                                         {
                                                             "incidenciaId": 201,
                                                             "fecha": "2024-02-03",
                                                             "contenido": "No enciende la pantalla del Pc 3",
                                                             "autor": "Carlos Ruiz"
                                                         }
                                                     ]
                                                 },
                                                 {
                                                     "fecha": "2024-02-03T12:00:00",
                                                     "titulo": "No enciende la pantalla",
                                                     "descripcion": "No enciende la pantalla del Pc 4",
                                                     "estado": "ABIERTA",
                                                     "urgencia": "Urgente",
                                                     "usuario": {
                                                         "id": 401,
                                                         "nombre": "Pablo Camara",
                                                         "username": "pabcam",
                                                         "password": "passwordpabcam",
                                                         "email": "pablo.camara@gmail.com",
                                                         "role": "USER"
                                                     },
                                                     "categoria": {
                                                         "id": 251,
                                                         "nombre": "Impresoras",
                                                         "nombreCategoriaPadre": "Informática",
                                                         "listaCategoriasHijas": []
                                                     },
                                                     "equipo": {
                                                         "id": 251,
                                                         "nombre": "Pc 4",
                                                         "caracteristicas": "No enciende la pantalla"
                                                     },
                                                     "ubicacion": {
                                                         "id": 101,
                                                         "nombre": "Aula 1ºAyF"
                                                     },
                                                     "notas": [
                                                         {
                                                             "incidenciaId": 251,
                                                             "fecha": "2024-02-03",
                                                             "contenido": "No enciende la pantalla del Pc 4",
                                                             "autor": "Pablo Camara"
                                                         }
                                                     ]
                                                 },
                                                 {
                                                     "fecha": "2024-02-03T12:00:00",
                                                     "titulo": "Pata rota",
                                                     "descripcion": "Pata rota de la mesa 1",
                                                     "estado": "ABIERTA",
                                                     "urgencia": "Urgente",
                                                     "usuario": {
                                                         "id": 501,
                                                         "nombre": "Pedro Sanchez",
                                                         "username": "pedsan",
                                                         "password": "passwordpedsan",
                                                         "email": "pedro.sanchez@gmail.com",
                                                         "role": "USER"
                                                     },
                                                     "categoria": {
                                                         "id": 251,
                                                         "nombre": "Impresoras",
                                                         "nombreCategoriaPadre": "Informática",
                                                         "listaCategoriasHijas": []
                                                     },
                                                     "equipo": {
                                                         "id": 301,
                                                         "nombre": "Mesa 1",
                                                         "caracteristicas": "Pata rota"
                                                     },
                                                     "ubicacion": {
                                                         "id": 151,
                                                         "nombre": "Aula 2ºAyF"
                                                     },
                                                     "notas": [
                                                         {
                                                             "incidenciaId": 301,
                                                             "fecha": "2024-02-03",
                                                             "contenido": "Pata rota de la mesa 1",
                                                             "autor": "Pedro Sanchez"
                                                         }
                                                     ]
                                                 },
                                                 {
                                                     "fecha": "2024-02-03T12:00:00",
                                                     "titulo": "Pata rota",
                                                     "descripcion": "Pata rota de la mesa 2",
                                                     "estado": "ABIERTA",
                                                     "urgencia": "Urgente",
                                                     "usuario": {
                                                         "id": 601,
                                                         "nombre": "Alvaro Castilla",
                                                         "username": "alvcas",
                                                         "password": "passwordalvcas",
                                                         "email": "alvaro.castilla@gmail.com",
                                                         "role": "USER"
                                                     },
                                                     "categoria": {
                                                         "id": 251,
                                                         "nombre": "Impresoras",
                                                         "nombreCategoriaPadre": "Informática",
                                                         "listaCategoriasHijas": []
                                                     },
                                                     "equipo": {
                                                         "id": 351,
                                                         "nombre": "Mesa 2",
                                                         "caracteristicas": "Pata rota"
                                                     },
                                                     "ubicacion": {
                                                         "id": 151,
                                                         "nombre": "Aula 2ºAyF"
                                                     },
                                                     "notas": [
                                                         {
                                                             "incidenciaId": 351,
                                                             "fecha": "2024-02-03",
                                                             "contenido": "Pata rota de la mesa 2",
                                                             "autor": "Alvaro Castilla"
                                                         }
                                                     ]
                                                 },
                                                 {
                                                     "fecha": "2024-02-03T12:00:00",
                                                     "titulo": "Pata rota",
                                                     "descripcion": "Pata rota de la mesa 3",
                                                     "estado": "ABIERTA",
                                                     "urgencia": "Urgente",
                                                     "usuario": {
                                                         "id": 1,
                                                         "nombre": "Moisés Dorado",
                                                         "username": "moidor",
                                                         "password": "passwordmoidor",
                                                         "email": "moi.dor@gmail.com",
                                                         "role": "USER"
                                                     },
                                                     "categoria": {
                                                         "id": 251,
                                                         "nombre": "Impresoras",
                                                         "nombreCategoriaPadre": "Informática",
                                                         "listaCategoriasHijas": []
                                                     },
                                                     "equipo": {
                                                         "id": 401,
                                                         "nombre": "Mesa 3",
                                                         "caracteristicas": "Pata rota"
                                                     },
                                                     "ubicacion": {
                                                         "id": 151,
                                                         "nombre": "Aula 2ºAyF"
                                                     },
                                                     "notas": [
                                                         {
                                                             "incidenciaId": 401,
                                                             "fecha": "2024-02-03",
                                                             "contenido": "Pata rota de la mesa 3",
                                                             "autor": "Moisés Dorado"
                                                         }
                                                     ]
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


    /*@ApiResponses(value = {
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
    }*/


}
