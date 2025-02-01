package com.salesianos.satapp.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@DiscriminatorValue("p")
public class Personal extends Usuario{

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

}
