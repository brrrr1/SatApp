package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.EditAlumnoDto;
import com.salesianos.satapp.dto.EditHistoricoCursosDto;
import com.salesianos.satapp.error.AlumnoNotFoundException;
import com.salesianos.satapp.model.Alumno;
import com.salesianos.satapp.model.HistoricoCursos;
import com.salesianos.satapp.repository.AlumnoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoService {
    private final AlumnoRepository alumnoRepository;

    public List<Alumno> findAll(){
        List<Alumno> result = alumnoRepository.findAll();
        if(result.isEmpty())
            throw new AlumnoNotFoundException("No se encontraron alumnos");
        return result;
    }

    public Alumno findById(Long id) {
        Optional<Alumno> result = alumnoRepository.findById(id);
        if(result.isEmpty())
            throw new AlumnoNotFoundException("No se encontró el alumno con id: " + id);
        else {
            return result.get();
        }
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

    public Alumno editAlumno(Long id, EditAlumnoDto editAlumnoDto) {
        Alumno alumno = findById(id);
        alumno.setNombre(editAlumnoDto.nombre());
        alumno.setEmail(editAlumnoDto.email());
        alumno.setRole(editAlumnoDto.role());
        alumno.setPassword(editAlumnoDto.password());
        alumno.setUsername(editAlumnoDto.username());

        alumno.getHistoricoCursos().clear();
        alumno.getHistoricoCursos().addAll(editAlumnoDto.historicoCursos());

        return alumnoRepository.save(alumno);
    }

    public HistoricoCursos saveHistoricoCurso(Long alumnoId, EditHistoricoCursosDto editHistoricoCursosDto) {
        Alumno alumno = alumnoRepository.findById(alumnoId).get();
        HistoricoCursos historicoCursos = HistoricoCursos.builder()
                .curso(editHistoricoCursosDto.curso())
                .cursoEscolar(editHistoricoCursosDto.cursoEscolar())
                .alumno(alumno)
                .build();
        alumno.getHistoricoCursos().add(historicoCursos);
        alumnoRepository.save(alumno);
        return historicoCursos;
    }

    public void delete(Long id) {
        alumnoRepository.deleteById(id);
    }



}