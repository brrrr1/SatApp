package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.*;
import com.salesianos.satapp.model.Categoria;
import com.salesianos.satapp.model.Ubicacion;
import com.salesianos.satapp.service.UbicacionService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ubicacion/")
@RequiredArgsConstructor
@Tag(name = "Ubicacion", description = "El controlador para gestionar ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @Operation(summary = "Obtiene todas las ubicaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las ubicaciones",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUbicacionDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Sala 1"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la ubicación",
                    content = @Content),
    })
    @GetMapping
    public List<GetUbicacionDto> getAll() {
        return ubicacionService.findAll().stream().map(GetUbicacionDto::of).toList();
    }

    @Operation(summary = "Obtiene una ubicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la ubicación",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUbicacionDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Sala 1"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la ubicación",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetUbicacionDto getById(@PathVariable Long id) {
        return GetUbicacionDto.of(ubicacionService.findById(id).get());
    }

    @Operation(summary = "Crea una ubicación (DA CÓDIGO 500 PERO SE CREA EXITOSAMENTE)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la ubicación",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUbicacionDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Sala 1"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la ubicación",
                    content = @Content),
    })
    @PostMapping
    public ResponseEntity<GetUbicacionDto> saveUbicacion(@RequestBody GetUbicacionDto nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetUbicacionDto.of(ubicacionService.saveUbicacion(nuevo)));
    }

    @Operation(summary = "Edita una ubicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la ubicacicón",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUbicacionDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Aula 2DAM"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha editado la ubicación",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetUbicacionDto> update(@PathVariable Long id,
                                                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                          description = "Cuerpo de la nota a editar", required = true,
                                                          content = @Content(mediaType = "application/json",
                                                                  schema = @Schema(implementation = GetUbicacionDto.class),
                                                                  examples = @ExampleObject(value = """
                                                                {
                                                                                  "nombre": "Aula 2DAM",
                                                                                  "equipos": []
                                                                              }
""")))
                                                  @RequestBody Ubicacion ubicacionActualizada) {
        return ResponseEntity.status(HttpStatus.OK).body(GetUbicacionDto.of(ubicacionService.editUbicacion(id, GetUbicacionDto.of(ubicacionActualizada))));
    }

    @Operation(summary = "Borra una ubicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado la ubicación",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ubicacion.class))
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido borrar la ubicación",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUbicacion(@PathVariable Long id) {
        ubicacionService.deleteUbicacion(id);
        return ResponseEntity.noContent().build();
    }
}
