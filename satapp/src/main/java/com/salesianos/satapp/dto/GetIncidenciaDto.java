package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Estado;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.model.Nota;
import com.salesianos.satapp.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

import com.salesianos.satapp.dto.GetNotaDto;

public record GetIncidenciaDto(
        LocalDateTime fecha,
        String titulo,
        String descripcion,
        Estado estado,
        String urgencia,
        Usuario usuario,
        List<GetNotaDto> notas
) {

    public static GetIncidenciaDto of(Incidencia incidencia) {
        return new GetIncidenciaDto(
                incidencia.getFecha(),
                incidencia.getTitulo(),
                incidencia.getDescripcion(),
                incidencia.getEstado(),
                incidencia.getUrgencia(),
                incidencia.getUsuario(),
                incidencia.getNotas().stream().map(GetNotaDto::of).toList()
        );
    }
}
