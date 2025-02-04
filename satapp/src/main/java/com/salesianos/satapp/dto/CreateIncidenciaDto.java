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
        List<Nota> notas
) {

    public static CreateIncidenciaDto to(Incidencia incidencia) {
        return new CreateIncidenciaDto(incidencia.getFecha(), incidencia.getTitulo(), incidencia.getDescripcion(), incidencia.getEstado(), incidencia.getUrgencia(), incidencia.getUsuario()/*, incidencia.getCategoria()*/, incidencia.getUbicacion(), incidencia.getNotas());
    }

}
