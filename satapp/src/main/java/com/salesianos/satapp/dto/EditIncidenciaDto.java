package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.*;

import java.time.LocalDateTime;

public record EditIncidenciaDto(
        LocalDateTime fecha,
        String titulo,
        String descripcion,
        String urgencia,
        Estado estado,
        Ubicacion ubicacion,
        Equipo equipo,
        Categoria categoria,
        Usuario usuario
) {
}