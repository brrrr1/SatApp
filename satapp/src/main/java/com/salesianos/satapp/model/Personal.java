package com.salesianos.satapp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("p")
public class Personal extends Usuario{

    private Tipo tipo;

}
