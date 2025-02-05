package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.CreateCategoriaDto;
import com.salesianos.satapp.dto.GetCategoriaDto;
import com.salesianos.satapp.dto.GetIncidenciaDto;
import com.salesianos.satapp.model.Categoria;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.service.CategoriaService;
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
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria/")
@RequiredArgsConstructor
@Tag(name = "Categoria", description = "Controlador de categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Obtiene todas las categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las categorías",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetIncidenciaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                    "nombre": "Electronica",
                                                    "incidencias": [],
                                                    "categoriaPadre": null,
                                                    "listaCategoriasHijas": []
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la categoría",
                    content = @Content),
    })
    @GetMapping("/")
    public List<GetCategoriaDto> getAll() {

        return categoriaService.findAll().stream().map(GetCategoriaDto::of).toList();

    }

    @Operation(summary = "Obtiene una categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la categoría",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetCategoriaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                    "nombre": "Electronica",
                                                    "incidencias": [],
                                                    "categoriaPadre": null,
                                                    "listaCategoriasHijas": []
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la categoría",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetCategoriaDto getById(@PathVariable Long id) {
        return GetCategoriaDto.of(categoriaService.findById(id).get());
    }

    @Operation(summary = "Crea una categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la categoría",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetCategoriaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                    "nombre": "Electrónica",
                                                    "incidencias": [],
                                                    "categoriaPadre": null,
                                                    "listaCategoriasHijas": []
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha creado la categoría",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<Categoria> create(@RequestBody CreateCategoriaDto categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoria));
    }

    @Operation(summary = "Edita una categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la categoría",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetCategoriaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                    "nombre": "Informatica",
                                                    "incidencias": [],
                                                    "categoriaPadre": null,
                                                    "listaCategoriasHijas": []
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha editado la categoría",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody CreateCategoriaDto categoria) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.update(id, categoria));
    }

    @Operation(summary = "Borra una categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado la categoría",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Categoria.class))
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido borrar la categoría",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
