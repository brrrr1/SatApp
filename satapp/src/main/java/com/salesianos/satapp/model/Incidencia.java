package com.salesianos.satapp.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Incidencia {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime fecha;
    private String titulo;
    private String descripcion;
    private enum estado {ABIERTA, EN_PROCESO, CERRADA};
    private String urgencia;

}
