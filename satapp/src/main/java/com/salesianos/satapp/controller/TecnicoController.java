package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.EditIncidenciaDto;
import com.salesianos.satapp.dto.EditTecnicoDto;
import com.salesianos.satapp.dto.GetIncidenciaDto;
import com.salesianos.satapp.dto.GetTecnicoDto;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.model.Tecnico;
import com.salesianos.satapp.service.IncidenciaService;
import com.salesianos.satapp.service.TecnicoService;
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
@RequestMapping("/tecnico/")
@RequiredArgsConstructor
@Tag(name = "Tecnico", description = "El controlador de técnico para gestionar todas las operaciones relacionadas con esta entidad")
public class TecnicoController {
    private final TecnicoService tecnicoService;
    private final IncidenciaService incidenciaService;

    @Operation(summary = "Obtiene todas los técnicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las técnicos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTecnicoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                    [
                                            {
                                                "id": 1,
                                                "nombre": "Messi",
                                                "username": "leomessi10",
                                                "password": "3443",
                                                "email": "messi@gmail.com",
                                                "role": "PERSONAL",
                                                "incidencias": []
                                            },
                                            {
                                                "id": 2,
                                                "nombre": "Messi",
                                                "username": "leomessi102",
                                                "password": "3443",
                                                "email": "messi2@gmail.com",
                                                "role": "PERSONAL",
                                                "incidencias": []
                                            }
                                    ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado tecnicos"
            )
    })
    @GetMapping
    public List<GetTecnicoDto> getAll(){
        return tecnicoService.findAll().stream().map(GetTecnicoDto::of).toList();

    }

    @Operation(summary = "Obtiene un técnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las tecnicos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetTecnicoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                            {
                                                "id": 1,
                                                "nombre": "Messi",
                                                "username": "leomessi10",
                                                "password": "3443",
                                                "email": "messi@gmail.com",
                                                "role": "PERSONAL",
                                                "incidencias": []
                                            },
                                            {
                                                "id": 2,
                                                "nombre": "Messi",
                                                "username": "leomessi102",
                                                "password": "3443",
                                                "email": "messi2@gmail.com",
                                                "role": "PERSONAL",
                                                "incidencias": []
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado tecnicos"
            )
    })
    @GetMapping("/{id}")
    public GetTecnicoDto getById(@PathVariable Long id){
        return GetTecnicoDto.of(tecnicoService.findById(id));
    }

    @Operation(summary = "Crea un técnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el técnico",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado el técnico",
                    content = @Content),
    })
    @PostMapping
    public GetTecnicoDto saveTecnico(@RequestBody EditTecnicoDto tecnicoNuevo) {
        Tecnico tecnico =  tecnicoService.saveTecnico(tecnicoNuevo);
        return GetTecnicoDto.of(tecnico);
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
                    description = "No se ha encontrado ningún técnico",
                    content = @Content),
    })
    @PutMapping("/incidencia/{id}")
    public ResponseEntity<Incidencia> gestionarIncidencia(
            @PathVariable Long incidenciaId,
            @RequestBody EditIncidenciaDto incidenciaDto) {

        Incidencia incidencia = tecnicoService.gestionarIncidencia(incidenciaId, incidenciaDto);

        return ResponseEntity.ok(incidencia);
    }

}
