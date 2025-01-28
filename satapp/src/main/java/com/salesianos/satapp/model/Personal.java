package com.salesianos.satapp.model;

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
public class Personal extends Usuario{

    private enum Tipo{
        PROFESOR,
        PAS
    }

}
