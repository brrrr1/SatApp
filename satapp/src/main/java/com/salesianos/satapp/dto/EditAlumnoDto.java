package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.HistoricoCursos;

import java.util.List;

public record EditAlumnoDto(
        String nombre,
        String username,
        String email,
        String password,
        String role,
        List<HistoricoCursos> historicoCursos
) {
}
