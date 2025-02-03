package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Ubicacion;

public record EditEquipoDto(
        String nombre,
        String caracteristicas,
        Ubicacion ubicacion
) {}
