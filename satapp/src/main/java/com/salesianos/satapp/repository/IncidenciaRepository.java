package com.salesianos.satapp.repository;

import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    @Query("""
        SELECT n
        FROM Incidencia i
        JOIN i.notas n
    """)
    List<Nota> findAllNotas();

    @Query("""
        SELECT n
        FROM Nota n
        WHERE n.id = ?1
           """)
    Optional<Nota> findByIdNota(Long id);

}
