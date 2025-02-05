package com.salesianos.satapp.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class HistoricoCursos {

    @Id @GeneratedValue
    private Long id;

    private String cursoEscolar;
    private String curso;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

}
