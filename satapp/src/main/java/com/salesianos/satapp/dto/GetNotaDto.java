package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Nota;

public record GetNotaDto(
        Long incidenciaId,
        java.time.LocalDateTime fecha,
        String contenido,
        String autor
) {

    public static GetNotaDto of(Nota nota) {
        return new GetNotaDto(
                nota.getIncidencia().getId(), nota.getFecha(), nota.getContenido(), nota.getAutor()
        );
    }
}