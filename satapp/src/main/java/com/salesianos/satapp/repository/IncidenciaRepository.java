package com.salesianos.satapp.repository;

import com.salesianos.satapp.model.Incidencia;
import com.salesianos.satapp.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
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
        FROM Incidencia i
        JOIN i.notas n
        WHERE n.id = :id
    """)
    Optional<Nota> findByIdNota(@Param("id") Long id);

    @Query("""
    SELECT i FROM Incidencia i 
    WHERE TYPE(i.usuario) = Alumno AND i.usuario.id = :alumnoId
    """)
    List<Incidencia> findByAlumnoId(@Param("alumnoId") Long alumnoId);


    @Query("""
    SELECT i FROM Incidencia i WHERE i.fecha BETWEEN :startDate AND :endDate
    """)
    List<Incidencia> findByFechaBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<Incidencia> findByUsuarioIdAndEstado(Long usuarioId, String estado);

    List<Incidencia> findByEquipoId(Long id);

    List<Incidencia> findByUbicacionId(Long idUbi);
}
