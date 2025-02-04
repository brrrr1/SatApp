package com.salesianos.satapp.error;

public class NotaNotFoundException extends RuntimeException {

    public NotaNotFoundException(String message) {
        super(message);
    }

    public NotaNotFoundException(Long id) {
        super("No hay ninguna nota con ese ID: " + id);
    }

    public NotaNotFoundException() {
        super("No hay nota con esos requisitos de búsqueda");
    }

}
