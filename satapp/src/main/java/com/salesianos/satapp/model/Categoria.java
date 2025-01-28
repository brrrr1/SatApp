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
