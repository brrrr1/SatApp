package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Categoria;

import java.util.List;

public record GetCategoriaDto(
        Long id,
        String nombre,
        String nombreCategoriaPadre,
        List<String> listaCategoriasHijas
) {

    public static GetCategoriaDto of(Categoria c) {
        return new GetCategoriaDto(c.getId(), c.getNombre(), c.getCategoriaPadre().getNombre(), c.getListaCategoriasHijas().stream().map(Categoria::getNombre).toList());
    }

}
