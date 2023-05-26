package com.numerus.ecoayudas.v1.app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Entity
@Table(name= "estados")
@Slf4j
public class EstadoSolicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    public EstadoSolicitud(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public EstadoSolicitud() {
    }
}
