package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Ubicacion;

public record GetUbicacionDto(
        Long id,
        String nombre
) {
    public static GetUbicacionDto of(Ubicacion ubicacion) {
        return new GetUbicacionDto(
                ubicacion.getId(),
                ubicacion.getNombre()
        );
    }
}
