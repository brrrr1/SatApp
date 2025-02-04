package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.*;

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
        GetCategoriaDto categoria,
        GetEquipoSinUbiDto equipo,
        GetUbicacionSinEquipoDto ubicacion,
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
                GetCategoriaDto.of(incidencia.getCategoria()),
                GetEquipoSinUbiDto.of(incidencia.getEquipo()),
                GetUbicacionSinEquipoDto.of(incidencia.getUbicacion()),
                incidencia.getNotas().stream().map(GetNotaDto::of).toList()
        );
    }
}
