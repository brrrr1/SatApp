package com.salesianos.satapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class HistoricoCursos {

    @Id
    private String cursoEscolar;
    private String curso;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
