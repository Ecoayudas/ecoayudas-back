package com.numerus.ecoayudas.v1.app.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * Represents a Solicitud entity.
 */
@Data
@Entity
@Table(name = "solicitudes")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Date fecha;
    @ManyToOne
    private EstadoSolicitud estado;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Instalador instalador;

    /**
     * Constructor for Solicitud.
     *
     * @param id          The ID of the Solicitud.
     * @param descripcion The descripcion of the Solicitud.
     * @param fecha       The fecha of the Solicitud.
     * @param estado      The estado of the Solicitud.
     * @param cliente     The cliente associated with the Solicitud.
     * @param instalador  The instalador associated with the Solicitud.
     */
    public Solicitud(Long id, String descripcion, Date fecha, EstadoSolicitud estado, Cliente cliente, Instalador instalador) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.instalador = instalador;
    }

    /**
     * Default constructor for Solicitud.
     */
    public Solicitud() {
    }
}
