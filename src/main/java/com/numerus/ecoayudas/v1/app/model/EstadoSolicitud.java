package com.numerus.ecoayudas.v1.app.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents an EstadoSolicitud entity.
 */
@Data
@Entity
@Table(name = "estados")
public class EstadoSolicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    /**
     * Constructor for EstadoSolicitud.
     *
     * @param id          The ID of the estado.
     * @param descripcion The descripcion of the estado.
     */
    public EstadoSolicitud(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Default constructor for EstadoSolicitud.
     */
    public EstadoSolicitud() {
    }
}
