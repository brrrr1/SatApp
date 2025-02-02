package com.salesianos.satapp.repository;

import com.salesianos.satapp.model.Alumno;
import com.salesianos.satapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("""
            select a from Alumno a
            join fetch a.historicoCursos
            """)
    List<Alumno> findAllCourses();

    @Query("""
            select a from Alumno a
            join fetch a.historicoCursos
            where a.id = :id
            """)
    Optional<Alumno> findByIdCourse(Long id);
}
