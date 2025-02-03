package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Categoria;
import lombok.Builder;

import java.util.List;
import java.util.concurrent.CancellationException;

public record CreateCategoriaDto(
        Long id,
        String nombre,
        Categoria categoriaPadre,
        List<Categoria> listaCategoriasHijas
) {

    public static CreateCategoriaDto of(Categoria c) {
        return new CreateCategoriaDto(
                c.getId(),
                c.getNombre(),
                c.getCategoriaPadre(),
                c.getListaCategoriasHijas()
        );
    }
}