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
@Entity
public class Equipo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String caracteristicas;

    @OneToMany(mappedBy = "categoria")
    private List<Incidencia> incidencias = new ArrayList<>();

}
