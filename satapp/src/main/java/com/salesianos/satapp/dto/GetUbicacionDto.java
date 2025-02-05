package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Equipo;
import com.salesianos.satapp.model.Ubicacion;

import java.util.List;

public record GetUbicacionDto(
        Long id,
        String nombre
) {
    public static GetUbicacionDto of(Ubicacion ubicacion) {
        return new GetUbicacionDto(
                ubicacion.getId(),
                ubicacion.getNombre()
                //ubicacion.getEquipos().stream().map(GetEquipoSinUbiDto::of).toList()
        );
    }
}