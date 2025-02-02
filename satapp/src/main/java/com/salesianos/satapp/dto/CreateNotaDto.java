package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Incidencia;

import java.time.LocalDate;

public record CreateNotaDto(
        Incidencia incidencia,
        LocalDate fecha,
        String contenido,
        String autor
) {
}
