package com.numerus.ecoayudas.v1.app.model;

import com.numerus.ecoayudas.v1.app.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name="clientes")
@Slf4j
public class Cliente  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private  String telefono;
    private  String email;
    private  String documentacion;
    private String role;
    private String password;
    private String username;



    @ManyToOne
    private  Instalador instalador;
    @OneToMany
    private  List<Solicitud> solicitudes;

    public Cliente(Long id, String dni, String nombre, String apellidos, String direccion, String telefono, String email, String documentacion, String role, String password, String username, Instalador instalador, List<Solicitud> solicitudes) {
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
        this.username = username;
        this.instalador = instalador;
        this.solicitudes = solicitudes;
    }

    public Cliente() {
    }
}
