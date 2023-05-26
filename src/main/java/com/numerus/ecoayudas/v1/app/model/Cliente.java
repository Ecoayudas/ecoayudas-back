package com.numerus.ecoayudas.v1.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Represents a Cliente entity.
 */
@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private String documentacion;
    private String role;
    private String password;


    @JsonIgnore
    @ManyToOne
    private Instalador instalador;
    @JsonIgnore
    @OneToMany
    private List<Solicitud> solicitudes;

    /**
     * Constructor for Cliente.
     *
     * @param id            The ID of the cliente.
     * @param dni           The DNI of the cliente.
     * @param nombre        The nombre of the cliente.
     * @param apellidos     The apellidos of the cliente.
     * @param direccion     The direccion of the cliente.
     * @param telefono      The telefono of the cliente.
     * @param email         The email of the cliente.
     * @param documentacion The documentacion of the cliente.
     * @param role          The role of the cliente.
     * @param password      The password of the cliente.
     * @param instalador    The associated instalador.
     * @param solicitudes   The list of solicitudes associated with the cliente.
     */

    public Cliente(Long id, String dni, String nombre, String apellidos, String direccion, String telefono, String email, String documentacion, String role, String password, Instalador instalador, List<Solicitud> solicitudes) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.documentacion = documentacion;
        this.role = role;
        this.password = password;
        this.instalador = instalador;
        this.solicitudes = solicitudes;
    }
    /**
     * Default constructor for Cliente.
     */
    public Cliente() {
    }
}
