package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.EditTecnicoDto;
import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.model.Tecnico;
import com.salesianos.satapp.repository.TecnicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;


    public List<Tecnico> findAll(){
        List<Tecnico> result = tecnicoRepository.findAll();
        if(result.isEmpty())
            throw new EntityNotFoundException("No hay tecnicos con esos criterios de busqueda");
        return result;
    }

    public Tecnico findById(Long id) {
        Optional<Tecnico> result = tecnicoRepository.findById(id);
        if(result.isEmpty())
            throw new EntityNotFoundException("No se encontró técnico con ese id");
        else {
            return result.get();
        }
    }





    public Tecnico saveTecnico(EditTecnicoDto editTecnicoCmd) {
        return tecnicoRepository.save(Tecnico.builder()
                .email(editTecnicoCmd.email())
                .role(editTecnicoCmd.role())
                .password(editTecnicoCmd.password())
                .username(editTecnicoCmd.username())
                .build());
    }



    public void delete(Long id) {
        tecnicoRepository.deleteById(id);
    }

}