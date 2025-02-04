package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.*;
import com.salesianos.satapp.model.Alumno;
import com.salesianos.satapp.model.HistoricoCursos;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.service.AlumnoService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alumno/")
@RequiredArgsConstructor
@Tag(name = "Alumno", description = "El controlador de alumno para gestionar todas las operaciones relacionadas con esta entidad")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Operation(summary = "Obtiene todas los alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las alumnos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                     "nombre": "Cristiano Ronaldo",
                                                     "username": "cr7",
                                                     "email": "cr7@triana.salesianos.com",
                                                     "password": "password123",
                                                     "role": "ALUMNO",
                                                     "historicoCursos": []
                                                 },
                                                 {
                                                     "nombre": "Cristiano Ronaldo",
                                                     "username": "cr7",
                                                     "email": "cr7@triana.salesianos.com",
                                                     "password": "password123",
                                                     "role": "ALUMNO",
                                                     "historicoCursos": []
                                                 }
                                             ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado alumnos"
            )
    })
    @GetMapping
    public List<GetAlumnoDto> getAll(){
        return alumnoService.findAll().stream().map(GetAlumnoDto::of).toList();

    }


    @Operation(summary = "Obtiene un alumno por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el alumno",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                          {
                                            "nombre": "Cristiano Ronaldo",
                                            "username": "cr7",
                                            "email": "cr7@triana.salesianos.com",
                                            "password": "password123",
                                            "role": "ALUMNO",
                                            "historicoCursos": []
                                           }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el alumno",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetAlumnoDto getById(@PathVariable Long id){
        return GetAlumnoDto.of(alumnoService.findById(id));
    }

    @Operation(summary = "Crea un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el alumno",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado el alumno",
                    content = @Content),
    })
    @PostMapping
    public GetAlumnoDto saveAlumno(@RequestBody EditAlumnoDto alumnoNuevo) {
        Alumno alumno = alumnoService.saveAlumno(alumnoNuevo);
        return GetAlumnoDto.of(alumno);
    }

    @Operation(summary = "Edita un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el alumno",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Incidencia.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún alumno",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetAlumnoDto update(@PathVariable Long id, @RequestBody EditAlumnoDto alumnoActualizado) {
        Alumno alumno = alumnoService.editAlumno(id, alumnoActualizado);
        return GetAlumnoDto.of(alumno);
    }

    @Operation(summary = "Crea el histórico de cursos de un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el histórico de cursos del alumno",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado el histórico de cursos del alumno",
                    content = @Content),
    })
    @PostMapping("/{alumnoId}/historico")
    public GetHistoricoCursosDto saveHistoricoCurso(@PathVariable Long alumnoId, @RequestBody EditHistoricoCursosDto historicoDto){
        HistoricoCursos historicoCursos = alumnoService.saveHistoricoCurso(alumnoId, historicoDto);

        return GetHistoricoCursosDto.of(historicoCursos);
    }

    @Operation(summary = "Borra un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado el alumno",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Incidencia.class)),
                            examples = {@ExampleObject(

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido borrar el alumno",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alumnoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
