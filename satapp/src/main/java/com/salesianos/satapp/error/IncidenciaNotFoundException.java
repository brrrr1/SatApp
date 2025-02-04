package com.salesianos.satapp.error;

public class IncidenciaNotFoundException extends RuntimeException {

    public IncidenciaNotFoundException(String message) {
        super(message);
    }

    public IncidenciaNotFoundException(Long id) {
        super("No hay ninguna incidencia con ese ID: " + id);
    }

    public IncidenciaNotFoundException() {
        super("No hay incidencia con esos requisitos de búsqueda");
    }

}
