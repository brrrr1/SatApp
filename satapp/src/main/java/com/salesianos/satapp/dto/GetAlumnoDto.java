package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Alumno;

import java.util.List;

public record GetAlumnoDto(
        String nombre,
        String username,
        String email,
        String password,
        String role,
        List<GetHistoricoCursosDto> historicoCursos
) {
    public static GetAlumnoDto of(Alumno alumno){
        return new GetAlumnoDto(
                alumno.getNombre(),
                alumno.getUsername(),
                alumno.getEmail(),
                alumno.getPassword(),
                alumno.getRole(),
                alumno.getHistoricoCursos().stream().map(GetHistoricoCursosDto::of).toList()

        );
    }
}
