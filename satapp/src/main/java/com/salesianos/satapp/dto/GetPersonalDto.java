package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Personal;
import com.salesianos.satapp.model.Tipo;

public record GetPersonalDto(
        Long id,
        String nombre,
        String username,
        String password,
        String email,
        String role,
        Tipo tipo
) {

    public static GetPersonalDto of(Personal p){
        return new GetPersonalDto(
                p.getId(),
                p.getNombre(),
                p.getUsername(),
                p.getPassword(),
                p.getEmail(),
                p.getRole(),
                p.getTipo()
        );
    }
}
