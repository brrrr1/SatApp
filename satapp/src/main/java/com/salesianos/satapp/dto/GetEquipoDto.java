package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Equipo;
import com.salesianos.satapp.model.Ubicacion;

public record GetEquipoDto(
        Long id,
        String nombre,
        String caracteristicas,
        GetUbicacionDto ubicacion  // Incluir la ubicación
) {
    public static GetEquipoDto of(Equipo equipo) {
        return new GetEquipoDto(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getCaracteristicas(),
                equipo.getUbicacion() != null ? GetUbicacionDto.of(equipo.getUbicacion()) : null
        );
    }
}
