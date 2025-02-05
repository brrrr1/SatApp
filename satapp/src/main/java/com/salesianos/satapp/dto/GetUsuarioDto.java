package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Usuario;

public record GetUsuarioDto(
        Long id,
        String nombre,
        String username,
        String password,
        String email,
        String role
) {

    public static GetUsuarioDto of(Usuario u){
        return new GetUsuarioDto(
                u.getId(),
                u.getNombre(),
                u.getUsername(),
                u.getPassword(),
                u.getEmail(),
                u.getRole()
        );
    }
}