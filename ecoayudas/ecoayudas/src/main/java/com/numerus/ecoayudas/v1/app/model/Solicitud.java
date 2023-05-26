package com.numerus.ecoayudas.v1.app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
@Slf4j
@Data
@Entity
@Table(name="solicitudes")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String descripcion;
    private Date fecha;
    @ManyToOne
    private EstadoSolicitud estado;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Instalador instalador;

    public Solicitud(Long id, String descripcion, Date fecha, EstadoSolicitud estado, Cliente cliente, Instalador instalador) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.instalador = instalador;
    }

    public Solicitud() {
    }
}
