package com.salesianos.satapp.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Equipo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String caracteristicas;

    @OneToMany(mappedBy = "categoria")
    private List<Incidencia> incidencias = new ArrayList<>();

}
