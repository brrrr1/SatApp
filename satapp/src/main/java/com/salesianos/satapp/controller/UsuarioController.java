package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.EditIncidenciaDto;
import com.salesianos.satapp.dto.EditUsuarioDto;
import com.salesianos.satapp.dto.GetIncidenciaDto;
import com.salesianos.satapp.dto.GetUsuarioDto;
import com.salesianos.satapp.model.Incidencia;
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

    @Operation(summary = "Obtiene todas los usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las usuarios",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUsuarioDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "username": "Rafahm03"
                                            }
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
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                          {
                                            "nombre": "Cristiano Ronaldo",
                                            "username": "cr7",
                                            "email": "cr7@triana.salesianos.com",
                                            "password": "password123",
                                            "role": "usuario",
                                            "historicoCursos": []
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

    @Operation(summary = "Abre una incidencia de un usuario")
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
    }

    @Operation(summary = "Crea un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado el usuario",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody EditUsuarioDto nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        usuarioService.save(nuevo));
    }

    @Operation(summary = "Edita un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Incidencia.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún usuario",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> edit(@PathVariable Long id, @RequestBody EditUsuarioDto editUsuarioDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.edit(id, editUsuarioDto));
    }

    @Operation(summary = "Borra un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado el usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Incidencia.class)),
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
