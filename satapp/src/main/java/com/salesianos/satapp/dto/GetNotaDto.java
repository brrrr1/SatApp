package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Nota;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record GetNotaDto(
        Long incidenciaId,
        LocalDate fecha,
        String contenido,
        String autor
) {

    public static GetNotaDto of(Nota nota) {
        return new GetNotaDto(
                nota.getIncidencia().getId(), nota.getFecha(), nota.getContenido(), nota.getAutor()
        );
    }
}