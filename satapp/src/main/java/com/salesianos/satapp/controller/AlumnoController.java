package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.EditAlumnoDto;
import com.salesianos.satapp.dto.EditHistoricoCursosDto;
import com.salesianos.satapp.dto.GetAlumnoDto;
import com.salesianos.satapp.dto.GetHistoricoCursosDto;
import com.salesianos.satapp.model.Alumno;
import com.salesianos.satapp.model.HistoricoCursos;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno/")
@RequiredArgsConstructor
@Tag(name = "Alumno", description = "El controlador de alumnos, para poder realizar todas las operaciones de gestión")
public class AlumnoController {

    private final UsuarioService alumnoService;

    @Operation(summary = "Obtiene todos los alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado alumnos",
                    content = {@Content(mediaType = "application/json",
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
                                                               "nombre": "Bruno Delgado",
                                                               "username": "br1",
                                                               "email": "delgado.hebru24@triana.salesianos.com",
                                                               "password": "777890",
                                                               "role": "ALUMNO",
                                                               "historicoCursos": []
                                                 }
                                             ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun alumno",
                    content = @Content),
    })
    @GetMapping
    public List<GetAlumnoDto> getAlumnos() {
        return alumnoService.findAllAlumnos().stream().map(GetAlumnoDto::of).toList();
    }

    @Operation(summary = "Obtiene un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el alumno",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
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
    public Alumno getAlumno() {
        return alumnoService.findAlumnoById(1L);
    }

    @Operation(summary = "Crea un nuevo alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el alumno",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                 {
                                                               "nombre": "Cristiano Ronaldo",
                                                               "username": "cr7",
                                                               "email": "cr7@gmail.com",
                                                                "password": "password123",
                                                                "role": "ALUMNO",
                                                                "historicoCursos": []
                                                 }
                                            """

                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear el alumno",
                    content = @Content),
    })
    @PostMapping
    public GetAlumnoDto saveAlumno(@RequestBody EditAlumnoDto alumnoN) {
        Alumno alumno = alumnoService.saveAlumno(alumnoN);
        return GetAlumnoDto.of(alumno);
    }


    @Operation(summary = "Crea un nuevo historico de curso para un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el historico de curso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetAlumnoDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado un historico de curso",
                    content = @Content),
    })
    @PostMapping("/{alumnoId}/historico")
    public GetHistoricoCursosDto saveHistoricoCurso(@PathVariable Long alumnoId, @RequestBody EditHistoricoCursosDto historicoDto){
        HistoricoCursos historicoCursos = alumnoService.saveHistoricoCurso(alumnoId, historicoDto);
        return GetHistoricoCursosDto.of(historicoCursos);
    }


}
