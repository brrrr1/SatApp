package com.salesianos.satapp.service;

import com.salesianos.satapp.dto.EditPersonalDto;
import com.salesianos.satapp.error.PersonalNotFoundException;
import com.salesianos.satapp.model.Personal;
import com.salesianos.satapp.repository.PersonalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalService {
    private final PersonalRepository personalRepository;

    public List<Personal> findAll(){
        List<Personal> result = personalRepository.findAll();
        if(result.isEmpty())
            throw new PersonalNotFoundException("No se encontraron personal");
        return result;
    }

    public Personal findById(Long id) {
        Optional<Personal> result = personalRepository.findById(id);
        if(result.isEmpty())
            throw new PersonalNotFoundException("No se ha encontrado el personal con id: " + id);
        else {
            return result.get();
        }
    }

    public Personal savePersonal(EditPersonalDto editPersonalCmd) {
        return personalRepository.save(Personal.builder()
                .nombre(editPersonalCmd.nombre())
                .email(editPersonalCmd.email())
                .role(editPersonalCmd.role())
                .password(editPersonalCmd.password())
                .username(editPersonalCmd.username())
                .tipo(editPersonalCmd.tipo())
                .build());
    }

    /*public void delete(Long id) {
        personalRepository.deleteById(id);
    }*/
}