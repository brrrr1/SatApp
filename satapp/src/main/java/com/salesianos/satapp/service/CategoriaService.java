package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.CreateCategoriaDto;
import com.salesianos.satapp.dto.CreateIncidenciaDto;
import com.salesianos.satapp.error.CategoriaNotFoundException;
import com.salesianos.satapp.model.Categoria;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        List <Categoria> results = categoriaRepository.findAll();

        if (results.isEmpty())
            throw new CategoriaNotFoundException("No se han encontrado categorias");
        return results;
    }

    public Optional<Categoria> findById(Long id) {
        Optional <Categoria> resultsOp = categoriaRepository.findById(id);

        if (resultsOp.isEmpty())
            throw new CategoriaNotFoundException("No se ha encontrado ninguna categoria con ese id");
        return resultsOp;
    }

    public Categoria save(CreateCategoriaDto c) {
        return categoriaRepository.save(Categoria.builder()
                .id(c.id())
                .nombre(c.nombre())
                .categoriaPadre(c.categoriaPadre())
                .listaCategoriasHijas(c.listaCategoriasHijas())
                .build());
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria update(Long id, CreateCategoriaDto c) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(c.nombre());
                    categoria.setCategoriaPadre(c.categoriaPadre());
                    categoria.setListaCategoriasHijas(c.listaCategoriasHijas());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new CategoriaNotFoundException("No se ha encontrado ninguna categoria con ese id"));
    }

}
