package com.numerus.ecoayudas.v1.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Represents an Instalador entity.
 */
@Data
@Entity
@Table(name = "instaladores")
public class Instalador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombre;
    private String contacto;
    private String direccion;
    private String telefono;
    private String email;
    private String documentacion;
    private String role;
    private String password;
    private String username;
    @JsonIgnore
    @OneToMany
    private List<Cliente> clientes;
    @JsonIgnore
    @OneToMany
    private List<Solicitud> solicitudes;

    /**
     * Add a Cliente to the list of clientes associated with the Instalador.
     *
     * @param cliente The Cliente to be added.
     */
    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Add a Solicitud to the list of solicitudes associated with the Instalador.
     *
     * @param solicitud The Solicitud to be added.
     */
    public void addSolicitud(Solicitud solicitud) {
        solicitudes.add(solicitud);
    }

    /**
     * Constructor for Instalador.
     *
     * @param id            The ID of the Instalador.
     * @param dni           The DNI of the Instalador.
     * @param nombre        The nombre of the Instalador.
     * @param contacto      The contacto of the Instalador.
     * @param direccion     The direccion of the Instalador.
     * @param telefono      The telefono of the Instalador.
     * @param email         The email of the Instalador.
     * @param documentacion The documentacion of the Instalador.
     * @param role          The role of the Instalador.
     * @param password      The password of the Instalador.
     * @param username      The username of the Instalador.
     * @param clientes      The list of clientes associated with the Instalador.
     * @param solicitudes   The list of solicitudes associated with the Instalador.
     */
    public Instalador(Long id, String dni, String nombre, String contacto, String direccion, String telefono, String email, String documentacion, String role, String password, String username, List<Cliente> clientes, List<Solicitud> solicitudes) {

        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.contacto = contacto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.documentacion = documentacion;
        this.role = role;
        this.password = password;
        this.username = username;
        this.clientes = clientes;
        this.solicitudes = solicitudes;
    }

    /**
     * Default constructor for Instalador.
     */
    public Instalador() {
    }
}
