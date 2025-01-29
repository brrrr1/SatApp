package com.salesianos.satapp.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Nota {

    @Id
    private LocalDateTime fecha;
    private String contenido;
    private String autor;

    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

}
