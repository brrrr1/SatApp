package com.salesianos.satapp.error;

public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(String message) {
        super(message);
    }

    public UsuarioNotFoundException(Long id) {
        super("No hay ningún usuario con ese ID: " + id);
    }

    public UsuarioNotFoundException() {
        super("No hay usuarios con esos requisitos de búsqueda");
    }

}
