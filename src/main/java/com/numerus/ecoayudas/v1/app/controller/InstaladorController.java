package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.service.InstaladorService;
import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Instalador;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.service.StorageService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The InstaladorController class is a REST controller that handles API requests related to instaladores.
 * It maps to the specified API version and allows cross-origin requests from "http://localhost:4200".
 */
@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class InstaladorController {
    public final InstaladorService instaladorService;
    public final StorageService storageService;

    /**
     * Constructor for InstaladorController.
     *
     * @param instaladorService The InstaladorServiceImpl to be used for handling instalador operations.
     * @param storageService        The StorageService to be used for file storage operations.
     */
    public InstaladorController(InstaladorService instaladorService, StorageService storageService) {
        this.instaladorService = instaladorService;
        this.storageService = storageService;
    }

    /**
     * Save a new Instalador.
     *
     * @param instalador The Instalador object to be saved.
     */
    @PostMapping("/instaladores")
    public void save(@RequestBody Instalador instalador) {
        instaladorService.save(instalador);
    }

    /**
     * Get all Instaladores.
     *
     * @return The list of Instaladores.
     */
    @Transactional(readOnly = true)
    @GetMapping("/instaladores")
    public List<Instalador> findAll() {
        return instaladorService.findAll();
    }

    /**
     * Get an Instalador by its ID.
     *
     * @param id The ID of the Instalador.
     * @return The Optional Instalador object.
     */
    @Transactional(readOnly = true)
    @GetMapping("/instaladores/{id}")
    public Optional<Instalador> findById(@PathVariable Long id) {
        return instaladorService.findById(id);
    }

    /**
     * Get all Clientes associated with an Instalador.
     *
     * @param id The ID of the Instalador.
     * @return The list of Clientes associated with the Instalador.
     */
    @Transactional(readOnly = true)
    @GetMapping("/instaladores/clientes/{id}")
    public List<Cliente> findAllClientes(@PathVariable Long id) {
        return instaladorService.findAllClientes(id);
    }

    /**
     * Get all Solicitudes associated with an Instalador.
     *
     * @param id The ID of the Instalador.
     * @return The list of Solicitudes associated with the Instalador.
     */
    @Transactional(readOnly = true)
    @GetMapping("/instaladores/solicitudes/{id}")
    public List<Solicitud> findAllSolicitudes(@PathVariable Long id) {
        return instaladorService.findAllSolcitudes(id);
    }

}
