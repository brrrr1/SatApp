package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.EditPersonalDto;
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
                            array = @ArraySchema(schema = @Schema(implementation = GetPersonalDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 451,
                                                      "nombre": "Pepe Segura",
                                                      "username": "pepseg",
                                                      "password": "passwordpepseg",
                                                      "email": "pepe.segura@gmail.com",
                                                      "role": "ADMIN",
                                                      "tipo": "PROFESOR"
                                                  },
                                                  {
                                                      "id": 551,
                                                      "nombre": "Pablo Tey",
                                                      "username": "pabtey",
                                                      "password": "passwordpabtey",
                                                      "email": "pablo.tey@gmail.com",
                                                      "role": "ADMIN",
                                                      "tipo": "PAS"
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
                            array = @ArraySchema(schema = @Schema(implementation = GetPersonalDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                    "id": 451,
                                                    "nombre": "Pepe Segura",
                                                    "username": "pepseg",
                                                    "password": "passwordpepseg",
                                                    "email": "pepe.segura@gmail.com",
                                                    "role": "ADMIN",
                                                    "tipo": "PROFESOR"
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
                            array = @ArraySchema(schema = @Schema(implementation = GetPersonalDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 602,
                                                "nombre": Miguel,
                                                "username": "miguelcamposdev",
                                                "password": "1234",
                                                "email": "miguelcampos@gmail.com",
                                                "role": "PERSONAL",
                                                "tipo": "PROFESOR"
                                            }
                                            """

                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado el miembro del personal",
                    content = @Content),
    })
    @PostMapping()
    public GetPersonalDto savePersonal(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Cuerpo del miembro del personal a crear", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditPersonalDto.class),
                            examples = @ExampleObject(value = """
    {
                                            "nombre": "Miguel",
                                            "username": "miguelcamposdev",
                                            "password": "1234",
                                            "email": "miguelcampos@gmail.com",
                                            "role": "PERSONAL",
                                            "tipo": "PROFESOR"
                                        }
""")))
            @RequestBody EditPersonalDto personalNuevo) {
        Personal personal =  personalService.savePersonal(personalNuevo);
        return GetPersonalDto.of(personal);
    }
}
