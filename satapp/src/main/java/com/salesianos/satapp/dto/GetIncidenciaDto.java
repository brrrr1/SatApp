package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Estado;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.model.Usuario;

import java.time.LocalDateTime;

public record GetIncidenciaDto(


        LocalDateTime fecha,
        String titulo,
        String descripcion,
        Estado estado,
        String urgencia,
        Usuario usuario
) {

    public static GetIncidenciaDto of(Incidencia incidencia) {
        return new GetIncidenciaDto(
                incidencia.getFecha(),
                incidencia.getTitulo(),
                incidencia.getDescripcion(),
                incidencia.getEstado(),
                incidencia.getUrgencia(),
                incidencia.getUsuario()
        );
    }
}
