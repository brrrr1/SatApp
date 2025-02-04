package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.*;

import java.time.LocalDateTime;
import java.util.List;

public record CreateIncidenciaDto(
        LocalDateTime fecha,
        String titulo,
        String descripcion,
        Estado estado,
        String urgencia,
        Usuario usuario,
        /*Categoria categoria,*/
        Ubicacion ubicacion,
        Equipo equipo,
        List<Nota> notas
) {

    public static CreateIncidenciaDto to (Incidencia i) {
        return new CreateIncidenciaDto(
                i.getFecha(),
                i.getTitulo(),
                i.getDescripcion(),
                i.getEstado(),
                i.getUrgencia(),
                i.getUsuario(),
                /*i.getCategoria(),*/
                i.getUbicacion(),
                i.getEquipo(),
                i.getNotas()
        );
    }

}
