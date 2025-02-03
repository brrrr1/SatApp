package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.*;

import java.time.LocalDateTime;

public record CreateIncidenciaDto(
        LocalDateTime fecha,
        String titulo,
        String descripcion,
        /*Estado estado,*/
        String urgencia
        /*Usuario usuario,
        Categoria categoria,
        Ubicacion ubicacion*/
) {

    public static CreateIncidenciaDto of(Incidencia i) {
        return new CreateIncidenciaDto(
                i.getFecha(),
                i.getTitulo(),
                i.getDescripcion(),
                /*i.getEstado(),*/
                i.getUrgencia()
                /*i.getUsuario(),
                i.getCategoria(),
                i.getUbicacion()*/
        );
    }

}
