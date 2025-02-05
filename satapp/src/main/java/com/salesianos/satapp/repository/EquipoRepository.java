package com.salesianos.satapp.repository;

import com.salesianos.satapp.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByUbicacionId(Long idUbi);
}
