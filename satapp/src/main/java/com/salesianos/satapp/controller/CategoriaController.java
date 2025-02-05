package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.CreateCategoriaDto;
import com.salesianos.satapp.dto.GetCategoriaDto;
import com.salesianos.satapp.model.Categoria;
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
                            array = @ArraySchema(schema = @Schema(implementation = GetCategoriaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1,
                                                    "nombre": "Informática",
                                                    "nombreCategoriaPadre": null,
                                                    "listaCategoriasHijas": [
                                                        {
                                                            "id": 201,
                                                            "nombre": "Ordenadores",
                                                            "listaCategoriasHijas": []
                                                        },
                                                        {
                                                            "id": 251,
                                                            "nombre": "Impresoras",
                                                            "listaCategoriasHijas": []
                                                        },
                                                        {
                                                            "id": 301,
                                                            "nombre": "Proyectores",
                                                            "listaCategoriasHijas": []
                                                        }
                                                    ]
                                                },
                                                {
                                                    "id": 51,
                                                    "nombre": "Electrónica",
                                                    "nombreCategoriaPadre": null,
                                                    "listaCategoriasHijas": [
                                                        {
                                                            "id": 351,
                                                            "nombre": "Cables",
                                                            "listaCategoriasHijas": []
                                                        },
                                                        {
                                                            "id": 401,
                                                            "nombre": "Placas Base",
                                                            "listaCategoriasHijas": []
                                                        },
                                                        {
                                                            "id": 451,
                                                            "nombre": "Pantallas",
                                                            "listaCategoriasHijas": []
                                                        }
                                                    ]
                                                },
                                                {
                                                    "id": 101,
                                                    "nombre": "Inmueble",
                                                    "nombreCategoriaPadre": null,
                                                    "listaCategoriasHijas": [
                                                        {
                                                            "id": 501,
                                                            "nombre": "Mesas",
                                                            "listaCategoriasHijas": []
                                                        },
                                                        {
                                                            "id": 551,
                                                            "nombre": "Sillas",
                                                            "listaCategoriasHijas": []
                                                        },
                                                        {
                                                            "id": 601,
                                                            "nombre": "Pizarras",
                                                            "listaCategoriasHijas": []
                                                        }
                                                    ]
                                                },
                                                {
                                                    "id": 151,
                                                    "nombre": "Material Escolar",
                                                    "nombreCategoriaPadre": null,
                                                    "listaCategoriasHijas": [
                                                        {
                                                            "id": 651,
                                                            "nombre": "Libros",
                                                            "listaCategoriasHijas": []
                                                        },
                                                        {
                                                            "id": 701,
                                                            "nombre": "Cuadernos",
                                                            "listaCategoriasHijas": []
                                                        },
                                                        {
                                                            "id": 751,
                                                            "nombre": "Bolígrafos",
                                                            "listaCategoriasHijas": []
                                                        }
                                                    ]
                                                },
                                                {
                                                    "id": 201,
                                                    "nombre": "Ordenadores",
                                                    "nombreCategoriaPadre": "Informática",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 251,
                                                    "nombre": "Impresoras",
                                                    "nombreCategoriaPadre": "Informática",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 301,
                                                    "nombre": "Proyectores",
                                                    "nombreCategoriaPadre": "Informática",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 351,
                                                    "nombre": "Cables",
                                                    "nombreCategoriaPadre": "Electrónica",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 401,
                                                    "nombre": "Placas Base",
                                                    "nombreCategoriaPadre": "Electrónica",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 451,
                                                    "nombre": "Pantallas",
                                                    "nombreCategoriaPadre": "Electrónica",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 501,
                                                    "nombre": "Mesas",
                                                    "nombreCategoriaPadre": "Inmueble",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 551,
                                                    "nombre": "Sillas",
                                                    "nombreCategoriaPadre": "Inmueble",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 601,
                                                    "nombre": "Pizarras",
                                                    "nombreCategoriaPadre": "Inmueble",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 651,
                                                    "nombre": "Libros",
                                                    "nombreCategoriaPadre": "Material Escolar",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 701,
                                                    "nombre": "Cuadernos",
                                                    "nombreCategoriaPadre": "Material Escolar",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 751,
                                                    "nombre": "Bolígrafos",
                                                    "nombreCategoriaPadre": "Material Escolar",
                                                    "listaCategoriasHijas": []
                                                },
                                                {
                                                    "id": 752,
                                                    "nombre": "Categoria 2",
                                                    "nombreCategoriaPadre": null,
                                                    "listaCategoriasHijas": []
                                                }
                                            ]
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
                                                    "id": 151,
                                                    "nombre": "Material Escolar",
                                                    "nombreCategoriaPadre": null,
                                                    "listaCategoriasHijas": [
                                                        {
                                                            "id": 651,
                                                            "nombre": "Libros",
                                                            "listaCategoriasHijas": []
                                                        },
                                                        {
                                                            "id": 701,
                                                            "nombre": "Cuadernos",
                                                            "listaCategoriasHijas": []
                                                        },
                                                        {
                                                            "id": 751,
                                                            "nombre": "Bolígrafos",
                                                            "listaCategoriasHijas": []
                                                        }
                                                    ]
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
                            array = @ArraySchema(schema = @Schema(implementation = CreateCategoriaDto.class)),
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
    @PostMapping
    public ResponseEntity<Categoria> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Cuerpo de la categoría", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateCategoriaDto.class),
                            examples = @ExampleObject(value = """
{
    "nombre": "Informática",
    "incidencias": [],
    "categoriaPadre": {
        "id": 1,
        "nombre": "Electrónica"
    },
    "listaCategoriasHijas": []
}
""")))
            @RequestBody CreateCategoriaDto categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoria));
    }

    @Operation(summary = "Edita una categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la categoría",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CreateCategoriaDto.class)),
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
    public ResponseEntity<Categoria> update(@PathVariable Long id,
                                            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                    description = "Cuerpo de la categoría a editar", required = true,
                                                    content = @Content(mediaType = "application/json",
                                                            schema = @Schema(implementation = CreateCategoriaDto.class),
                                                            examples = @ExampleObject(value = """
    {
        "id": 1,
        "nombre": "Enchufes",
        "nombreCategoriaPadre": [],
        "listaCategoriasHijas": []
    }
""")))
                                            @RequestBody CreateCategoriaDto categoria) {
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
