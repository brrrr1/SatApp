package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Equipo;
import com.salesianos.satapp.model.Ubicacion;

public record GetEquipoSinUbiDto(
        Long id,
        String nombre,
        String caracteristicas

) {
    public static GetEquipoSinUbiDto of(Equipo equipo) {
        return new GetEquipoSinUbiDto(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getCaracteristicas()

        );
    }
}
