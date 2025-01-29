package com.salesianos.satapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Nota {

    private LocalDateTime fecha;
    private String contenido;
    private String autor;

    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

}
