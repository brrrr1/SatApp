package com.salesianos.satapp.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class HistoricoCursos {

    private String cursoEscolar;
    private String curso;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
