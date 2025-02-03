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
        String nombreCategoriaPadre = (c.getCategoriaPadre() != null) ? c.getCategoriaPadre().getNombre() : null;
        List<String> listaCategoriasHijas = c.getListaCategoriasHijas().stream()
                .map(Categoria::getNombre)
                .toList();
        return new GetCategoriaDto(c.getId(), c.getNombre(), nombreCategoriaPadre, listaCategoriasHijas);
    }

}
