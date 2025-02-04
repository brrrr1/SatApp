package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Estado;

import java.time.LocalDateTime;

public record EditIncidenciaDto(
        LocalDateTime fecha,
        String titulo,
        String descripcion,
        String urgencia,
        Estado estado,
        Long ubicacionId,
        Long equipoId,
        Long usuarioId
) {
}