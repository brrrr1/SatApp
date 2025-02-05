package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Equipo;
import com.salesianos.satapp.model.Ubicacion;

public record GetEquipoDto(
        Long id,
        String nombre,
        String caracteristicas,
        GetUbicacionSinEquipoDto ubicacion
) {
    public static GetEquipoDto of(Equipo equipo) {
        return new GetEquipoDto(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getCaracteristicas(),
                equipo.getUbicacion() != null ? GetUbicacionSinEquipoDto.of(equipo.getUbicacion()) : null
        );
    }
}
