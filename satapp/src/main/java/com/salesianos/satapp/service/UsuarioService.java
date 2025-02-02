package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.EditAlumnoDto;
import com.salesianos.satapp.dto.EditHistoricoCursosDto;
import com.salesianos.satapp.model.Alumno;
import com.salesianos.satapp.model.HistoricoCursos;
import com.salesianos.satapp.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository alumnoRepository;


    @Transactional
    public List<Alumno> findAllAlumnos() {
        List<Alumno> result = alumnoRepository.findAllCourses();

        if (result.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron alumnos");
        } else {
            return result;
        }
    }

    public Alumno findAlumnoById(Long id) {
        Alumno result = alumnoRepository
                .findByIdCourse(id)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado"));
        return result;
    }

    public Alumno saveAlumno(EditAlumnoDto editAlumnoDto) {
        return alumnoRepository.save(Alumno.builder()
                .nombre(editAlumnoDto.nombre())
                .email(editAlumnoDto.email())
                .role(editAlumnoDto.role())
                .password(editAlumnoDto.password())
                .username(editAlumnoDto.username())
                .historicoCursos(editAlumnoDto.historicoCursos())
                .build());
    }


    public HistoricoCursos saveHistoricoCurso(Long alumnoId, EditHistoricoCursosDto editHistoricoDto) {
        Alumno alumno = findAlumnoById(alumnoId);
        HistoricoCursos historicoCursos = HistoricoCursos.builder()
                .curso(editHistoricoDto.curso())
                .cursoEscolar(editHistoricoDto.cursoEscolar())
                .alumno(alumno)
                .build();
        alumno.getHistoricoCursos().add(historicoCursos);
        alumnoRepository.save(alumno);
        return historicoCursos;
    }
}
