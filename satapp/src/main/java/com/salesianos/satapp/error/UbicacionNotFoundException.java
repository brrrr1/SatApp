package com.salesianos.satapp.error;

public class UbicacionNotFoundException extends RuntimeException {

    public UbicacionNotFoundException(String message) {
        super(message);
    }

    public UbicacionNotFoundException(Long id) {
        super("No hay ninguna ubicación con ese ID: " + id);
    }

    public UbicacionNotFoundException() {
        super("No hay ubicaciones con esos requisitos de búsqueda");
    }

}
