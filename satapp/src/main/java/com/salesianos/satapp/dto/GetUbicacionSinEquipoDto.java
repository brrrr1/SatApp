package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Ubicacion;

public record GetUbicacionSinEquipoDto(
        Long id,
        String nombre
) {
    public static GetUbicacionSinEquipoDto of(Ubicacion ubicacion) {
        return new GetUbicacionSinEquipoDto(
                ubicacion.getId(),
                ubicacion.getNombre()
        );
    }
}
