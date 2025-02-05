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
    public ResponseEntity<List<GetUbicacionDto>> findAll() {
        return ResponseEntity.ok(ubicacionService.findAll().stream().map(GetUbicacionDto::of).collect(Collectors.toList()));
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
    public ResponseEntity<GetUbicacionDto> findById(@PathVariable Long id) {
        return ubicacionService.findById(id)
                .map(ubicacion -> ResponseEntity.ok(GetUbicacionDto.of(ubicacion)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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
    public ResponseEntity<GetUbicacionDto> create(@RequestBody CreateUbicacionDto createUbicacionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetUbicacionDto.of(ubicacionService.save(createUbicacionDto)));
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
                                                "nombre": "Sala de reuniones"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha editado la ubicación",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetUbicacionDto> update(@PathVariable Long id, @RequestBody Ubicacion ubicacionActualizada) {
        return ResponseEntity.status(HttpStatus.OK).body(GetUbicacionDto.of(ubicacionService.update(id, ubicacionActualizada)));
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
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ubicacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
