package com.salesianos.satapp.dto;

import com.salesianos.satapp.model.Tipo;

public record EditPersonalDto(
        String username,
        String password,
        String email,
        String role,
        Tipo tipo
) {
}
