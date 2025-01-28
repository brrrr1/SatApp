package com.salesianos.satapp.model;

import jakarta.persistence.*;
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
