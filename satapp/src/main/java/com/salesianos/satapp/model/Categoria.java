package com.salesianos.satapp.model;

import com.salesianos.satapp.dto.CreateCategoriaDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<Incidencia> incidencias = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "categoria_relacion_id",
            foreignKey = @ForeignKey(name = "fk_categoria_padre_categoria"))
    private Categoria categoriaPadre;

    @OneToMany(mappedBy = "categoriaPadre",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<Categoria> listaCategoriasHijas = new ArrayList<>();

}
