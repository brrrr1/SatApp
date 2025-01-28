package com.salesianos.satapp.repository;

import com.salesianos.satapp.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {
}
