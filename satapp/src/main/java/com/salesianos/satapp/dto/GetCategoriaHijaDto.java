package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public record GetCategoriaHijaDto(
        Long id,
        String nombre,
        List<GetCategoriaHijaDto> listaCategoriasHijas // Recursión controlada
) {

    public static GetCategoriaHijaDto of(Categoria c) {
        List<GetCategoriaHijaDto> listaCategoriasHijas = c.getListaCategoriasHijas().stream()
                .map(GetCategoriaHijaDto::of) // Convertimos recursivamente las hijas
                .collect(Collectors.toList());

        return new GetCategoriaHijaDto(
                c.getId(),
                c.getNombre(),
                listaCategoriasHijas
        );
    }

}