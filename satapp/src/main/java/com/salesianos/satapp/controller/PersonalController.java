package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.EditPersonalDto;
import com.salesianos.satapp.dto.GetAlumnoDto;
import com.salesianos.satapp.dto.GetIncidenciaDto;
import com.salesianos.satapp.dto.GetPersonalDto;
import com.salesianos.satapp.model.Personal;
import com.salesianos.satapp.service.PersonalService;
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
@RequestMapping("/personal/")
@RequiredArgsConstructor
@Tag(name = "Personal", description = "El controlador de personal para gestionar todas las operaciones relacionadas con esta entidad")
public class PersonalController {
    private final PersonalService personalService;

    @Operation(summary = "Obtiene todos los miembros del personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los miembros del personal",
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
                                                     "role": "PERSONAL",
                                                 },
                                                 {
                                                     "nombre": "Cristiano Ronaldo",
                                                     "username": "cr7",
                                                     "email": "cr7@triana.salesianos.com",
                                                     "password": "password123",
                                                     "role": "PERSONAL",        
                                                 }
                                             ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado miembros del personal"
            )
    })
    @GetMapping
    public List<GetPersonalDto> getAll(){
        return personalService.findAll().stream().map(GetPersonalDto::of).toList();

    }

    @Operation(summary = "Obtiene un miembro del personal por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el miembro del personal",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                          {
                                            "nombre": "Cristiano Ronaldo",
                                            "username": "cr7",
                                            "email": "cr7@triana.salesianos.com",
                                            "password": "password123",
                                            "role": "PROFESOR",
                                           }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el miembro del personal",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetPersonalDto getById(@PathVariable Long id){
        return GetPersonalDto.of(personalService.findById(id));
    }

    @Operation(summary = "Crea un miembro del personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha creado el miembro del personal",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado el miembro del personal",
                    content = @Content),
    })
    @PostMapping()
    public GetPersonalDto savePersonal(@RequestBody EditPersonalDto personalNuevo) {
        Personal personal =  personalService.savePersonal(personalNuevo);
        return GetPersonalDto.of(personal);
    }
}
