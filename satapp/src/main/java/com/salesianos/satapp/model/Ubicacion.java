package com.salesianos.satapp.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ubicacion {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "ubicacion")
    private List<Incidencia> incidencias = new ArrayList<>();

    @OneToMany(mappedBy = "ubicacion")
    private List<Equipo> equipos = new ArrayList<>(); // Relación con equipos
}
