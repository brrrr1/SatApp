package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Tecnico;

import java.util.List;

public record GetTecnicoDto(
        Long id,
        String nombre,
        String username,
        String password,
        String email,
        String role,
        List<GetIncidenciaDto> incidencias
) {
    public static GetTecnicoDto of(Tecnico t){
        return new GetTecnicoDto(
                t.getId(),
                t.getNombre(),
                t.getUsername(),
                t.getPassword(),
                t.getEmail(),
                t.getRole(),
                t.getIncidencias().stream().map(GetIncidenciaDto::of).toList()
        );
    }
}