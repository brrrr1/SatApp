package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.EditUsuarioDto;
import com.salesianos.satapp.dto.GetUsuarioDto;
import com.salesianos.satapp.model.Usuario;
import com.salesianos.satapp.service.UsuarioService;
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
@RequestMapping("/usuario/")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "El controlador de usuario para gestionar todas las operaciones relacionadas con esta entidad")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Obtiene todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las usuarios",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUsuarioDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1,
                                                    "nombre": "Moisés Dorado",
                                                    "username": "moidor",
                                                    "password": "passwordmoidor",
                                                    "email": "moi.dor@gmail.com",
                                                    "role": "USER"
                                                },
                                                {
                                                    "id": 101,
                                                    "nombre": "Carlos Roman",
                                                    "username": "carrom",
                                                    "password": "passwordcarrom",
                                                    "email": "carlos.roman@gmail.com",
                                                    "role": "USER"
                                                },
                                                {
                                                    "id": 201,
                                                    "nombre": "Manuel Maman",
                                                    "username": "manmam",
                                                    "password": "passwordmanmam",
                                                    "email": "manuel.maman@gmail.com",
                                                    "role": "USER"
                                                },
                                                {
                                                    "id": 301,
                                                    "nombre": "Carlos Ruiz",
                                                    "username": "carrui",
                                                    "password": "passwordcarrui",
                                                    "email": "carlos.ruiz@gmail.com",
                                                    "role": "USER"
                                                },
                                                {
                                                    "id": 401,
                                                    "nombre": "Pablo Camara",
                                                    "username": "pabcam",
                                                    "password": "passwordpabcam",
                                                    "email": "pablo.camara@gmail.com",
                                                    "role": "USER"
                                                },
                                                {
                                                    "id": 501,
                                                    "nombre": "Pedro Sanchez",
                                                    "username": "pedsan",
                                                    "password": "passwordpedsan",
                                                    "email": "pedro.sanchez@gmail.com",
                                                    "role": "USER"
                                                },
                                                {
                                                    "id": 601,
                                                    "nombre": "Alvaro Castilla",
                                                    "username": "alvcas",
                                                    "password": "passwordalvcas",
                                                    "email": "alvaro.castilla@gmail.com",
                                                    "role": "USER"
                                                },
                                                {
                                                    "id": 251,
                                                    "nombre": "Victor Levic",
                                                    "username": "viclev",
                                                    "password": "passwordviclev",
                                                    "email": "victor.levic@gmail.com",
                                                    "role": "ADMIN"
                                                },
                                                {
                                                    "id": 151,
                                                    "nombre": "Joaquin Carrascal",
                                                    "username": "joacar",
                                                    "password": "passwordjoacar",
                                                    "email": "joaquin.carrascal@gmail.com",
                                                    "role": "ADMIN"
                                                },
                                                {
                                                    "id": 551,
                                                    "nombre": "Pablo Tey",
                                                    "username": "pabtey",
                                                    "password": "passwordpabtey",
                                                    "email": "pablo.tey@gmail.com",
                                                    "role": "ADMIN"
                                                },
                                                {
                                                    "id": 351,
                                                    "nombre": "Rafa Hernandez",
                                                    "username": "rafher",
                                                    "password": "passwordrafher",
                                                    "email": "rafa.hernandez@gmail.com",
                                                    "role": "ADMIN"
                                                },
                                                {
                                                    "id": 51,
                                                    "nombre": "Bruno Delgado",
                                                    "username": "brudel",
                                                    "password": "passwordbrudel",
                                                    "email": "bruno.delgado@gmail.com",
                                                    "role": "ADMIN"
                                                },
                                                {
                                                    "id": 451,
                                                    "nombre": "Pepe Segura",
                                                    "username": "pepseg",
                                                    "password": "passwordpepseg",
                                                    "email": "pepe.segura@gmail.com",
                                                    "role": "ADMIN"
                                                }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado usuarios"
            )
    })
    @GetMapping
    public List<GetUsuarioDto> getAll(){
        return usuarioService.findAll().stream().map(GetUsuarioDto::of).toList();

    }


    @Operation(summary = "Obtiene un usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUsuarioDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                          {
                                                    "id": 351,
                                                    "nombre": "Rafa Hernandez",
                                                    "username": "rafher",
                                                    "password": "passwordrafher",
                                                    "email": "rafa.hernandez@gmail.com",
                                                    "role": "ADMIN"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el usuario",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetUsuarioDto getById(@PathVariable Long id){
        return GetUsuarioDto.of(usuarioService.findById(id));
    }

    /*@Operation(summary = "Abre una incidencia de un usuario")
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
    @PostMapping("/{id}/incidencia")
    public ResponseEntity<GetIncidenciaDto> abrirIncidencia(@PathVariable Long usuarioId, @RequestBody EditIncidenciaDto incidenciaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaDto.of(usuarioService.abrirIncidencia(usuarioId, incidenciaDto)));
    }*/

    @Operation(summary = "Crea un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)),
                            examples = {@ExampleObject(
                                    value= """
                                            {
                                                 "id": 603,
                                                 "nombre": "Cristiano Ronaldo",
                                                 "username": "cr7",
                                                 "password": "password123",
                                                 "email": "cr7@triana.salesianos.com",
                                                 "role": "ALUMNO",
                                                 "incidencias": []
                                             } 
                                           """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado el usuario",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<Usuario> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Cuerpo del usuario a crear", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditUsuarioDto.class),
                            examples = @ExampleObject(value = """
    {
                                            "nombre": "Cristiano Ronaldo",
                                            "username": "cr7",
                                            "password": "password123",
                                            "role": "ALUMNO",
                                            "email": "cr7@triana.salesianos.com"
                                        }  \s
""")))
            @RequestBody EditUsuarioDto nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        usuarioService.save(nuevo));
    }

    @Operation(summary = "Edita un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUsuarioDto.class)),
                            examples = {@ExampleObject(
                            value= """
                                    {
                                         "id": 1,
                                         "nombre": "Cristiano Messi",
                                         "username": "cr7",
                                         "password": "password123",
                                         "email": "cr7@triana.salesianos.com",
                                         "role": "ALUMNO"
                                     }
                                   """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún usuario",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetUsuarioDto> edit(@PathVariable Long id,
                                              @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                      description = "Cuerpo del usuario a editar", required = true,
                                                      content = @Content(mediaType = "application/json",
                                                              schema = @Schema(implementation = EditUsuarioDto.class),
                                                              examples = @ExampleObject(value = """
    {
                                                                              "id": 1,
                                                                              "nombre": "Cristiano Messi",
                                                                              "username": "cr7",
                                                                              "password": "password123",
                                                                              "email": "cr7@triana.salesianos.com",
                                                                              "role": "ALUMNO",
                                                                              "incidencias": []
                                                                          }
""")))
                                              @RequestBody EditUsuarioDto editUsuarioDto) {
        return ResponseEntity.status(HttpStatus.OK).body(GetUsuarioDto.of(usuarioService.edit(id, editUsuarioDto)));
    }

    @Operation(summary = "Borra un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado el usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido borrar el usuario",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
