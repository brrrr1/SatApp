package com.salesianos.satapp.repository;

import com.salesianos.satapp.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
