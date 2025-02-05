package com.salesianos.satapp.error;

public class AlumnoNotFoundException extends RuntimeException {

    public AlumnoNotFoundException(String message) {
        super(message);
    }

    public AlumnoNotFoundException(Long id) {
        super("No hay ningún alumnos con ese ID: " + id);
    }

    public AlumnoNotFoundException() {
        super("No hay alumnos con esos requisitos de búsqueda");
    }

}