package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Categoria;

import java.util.List;

public record GetCategoriaDto(
        Long id,
        String nombre,
        String nombreCategoriaPadre,
        List<GetCategoriaHijaDto> listaCategoriasHijas
) {

    public static GetCategoriaDto of(Categoria c) {
        String nombreCategoriaPadre = (c.getCategoriaPadre() != null) ? c.getCategoriaPadre().getNombre() : null;
        List<GetCategoriaHijaDto> listaCategoriasHijas = c.getListaCategoriasHijas().stream()
                .map(GetCategoriaHijaDto::of)
                .toList();

        return new GetCategoriaDto(c.getId(), c.getNombre(), nombreCategoriaPadre, listaCategoriasHijas);
    }

}