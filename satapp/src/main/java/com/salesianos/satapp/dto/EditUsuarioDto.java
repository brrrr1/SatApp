package com.salesianos.satapp.dto;

public record EditUsuarioDto(
        String nombre,
        String username,
        String password,
        String role,
        String email
) {
}