package com.salesianos.satapp.error;

public class TecnicoNotFoundException extends RuntimeException {

    public TecnicoNotFoundException(String message) {
        super(message);
    }

    public TecnicoNotFoundException(Long id) {
        super("No hay ningún técnico con ese ID: " + id);
    }

    public TecnicoNotFoundException() {
        super("No hay técnicos con esos requisitos de búsqueda");
    }
}
