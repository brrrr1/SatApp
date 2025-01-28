package com.salesianos.satapp.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String username;
    private String password;
    private String email;
    private String role;

    @OneToMany(mappedBy = "usuario",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidenciasReportadas = new ArrayList<>();

}
