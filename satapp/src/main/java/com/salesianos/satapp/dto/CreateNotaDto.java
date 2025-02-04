package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Incidencia;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateNotaDto(
        Incidencia incidencia,
        LocalDateTime fecha,
        String contenido,
        String autor
) {
}
