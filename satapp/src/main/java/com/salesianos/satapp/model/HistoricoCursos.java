package com.salesianos.satapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistoricoCursos {

    private String cursoEscolar;
    private String curso;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
