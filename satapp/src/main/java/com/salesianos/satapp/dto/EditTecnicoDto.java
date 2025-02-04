package com.salesianos.satapp.dto;

public record EditTecnicoDto(
        String nombre,
        String username,
        String password,
        String email,
        String role
) {
}