package com.salesianos.satapp.error;

public class EquipoNotFoundException extends RuntimeException {

    public EquipoNotFoundException(String message) {
        super(message);
    }

    public EquipoNotFoundException(Long id) {
        super("No hay ningún equipo con ese ID: " + id);
    }

    public EquipoNotFoundException() {
        super("No hay equipos con esos requisitos de búsqueda");
    }

}
