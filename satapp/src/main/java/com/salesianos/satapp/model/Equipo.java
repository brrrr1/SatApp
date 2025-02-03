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
public class Equipo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String caracteristicas;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @OneToMany(mappedBy = "equipo")
    private List<Incidencia> incidencias = new ArrayList<>();

}
