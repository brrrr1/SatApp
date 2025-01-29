package com.salesianos.satapp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("a")
public class Alumno extends Usuario{
    

}
