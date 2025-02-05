package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Ubicacion;

public record CreateUbicacionDto(
        String nombre
) {

    public static CreateUbicacionDto of(Ubicacion ubi) {
        return new CreateUbicacionDto(ubi.getNombre());
    }

}
