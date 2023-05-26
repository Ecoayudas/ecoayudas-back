package com.numerus.ecoayudas.v1.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.numerus.ecoayudas.v1.app.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Data
@Entity
@Table(name="instaladores")
public class Instalador  {
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

   public void addCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public void addSolicitud(Solicitud solicitud){
        solicitudes.add(solicitud);
    }

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

    public Instalador() {
    }
}
