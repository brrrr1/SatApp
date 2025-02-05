package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.HistoricoCursos;

public record GetHistoricoCursosDto(
        String curso,
        String cursoEscolar
) {
    public static GetHistoricoCursosDto of(HistoricoCursos historicoCursos) {
        return new GetHistoricoCursosDto(
                historicoCursos.getCurso(),
                historicoCursos.getCursoEscolar()
        );
    }
}
