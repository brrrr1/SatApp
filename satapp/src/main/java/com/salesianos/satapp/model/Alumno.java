package com.salesianos.satapp.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("a")
public class Alumno extends Usuario{

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (mappedBy = "alumno",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<HistoricoCursos> historicoCursos;

    public void addHistoricoCursos(HistoricoCursos historicoCursos){
        this.historicoCursos.add(historicoCursos);
        historicoCursos.setAlumno(this);
    }

    public void removeHistoricoCursos(HistoricoCursos historicoCursos){
        this.historicoCursos.remove(historicoCursos);
        historicoCursos.setAlumno(null);
    }
    

}
