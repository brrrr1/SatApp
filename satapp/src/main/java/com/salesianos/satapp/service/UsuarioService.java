package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.EditAlumnoDto;
import com.salesianos.satapp.dto.EditHistoricoCursosDto;
import com.salesianos.satapp.dto.EditUsuarioDto;
import com.salesianos.satapp.model.Alumno;
import com.salesianos.satapp.model.HistoricoCursos;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.model.Usuario;
import com.salesianos.satapp.repository.IncidenciaRepository;
import com.salesianos.satapp.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;


    public List<Usuario> findAll(){
        List<Usuario> result = usuarioRepository.findAll();
        if(result.isEmpty())
            throw new EntityNotFoundException("No hay usuarios con esos criterios de busqueda");
        return result;
    }

    public Usuario findById(Long id) {
        Optional<Usuario> result = usuarioRepository.findById(id);
        if(result.isEmpty())
            throw new EntityNotFoundException("No se encontraron usuarios con ese id");
        else {
            return result.get();
        }
    }

    public Usuario save(EditUsuarioDto nuevo) {
        return usuarioRepository.save(Usuario.builder()
                .username(nuevo.username())
                .password(nuevo.password())
                .email(nuevo.email())
                .role(nuevo.role())
                .build());
    }


    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

}