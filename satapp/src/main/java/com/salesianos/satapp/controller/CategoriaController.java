package com.salesianos.satapp.controller;

import com.salesianos.satapp.dto.CreateCategoriaDto;
import com.salesianos.satapp.dto.GetCategoriaDto;
import com.salesianos.satapp.model.Categoria;
import com.salesianos.satapp.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria/")
@RequiredArgsConstructor
@Tag(name = "Categoria", description = "Controlador de categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/")
    public List<GetCategoriaDto> getAll() {

        return categoriaService.findAll().stream().map(GetCategoriaDto::of).toList();

    }

    @GetMapping("/{id}")
    public GetCategoriaDto getById(@PathVariable Long id) {
        return GetCategoriaDto.of(categoriaService.findById(id).get());
    }

    @PostMapping("/")
    public ResponseEntity<Categoria> create(@RequestBody CreateCategoriaDto categoria) {
        return ResponseEntity.status(201).body(categoriaService.save(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody CreateCategoriaDto categoria) {
        return ResponseEntity.status(201).body(categoriaService.update(id, categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
