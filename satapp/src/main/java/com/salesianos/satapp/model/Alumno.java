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
@DiscriminatorValue("a")
public class Alumno extends Usuario{
    

}
