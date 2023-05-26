package com.numerus.ecoayudas.v1.app.controller;


import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.service.ClienteService;
import com.numerus.ecoayudas.v1.app.service.StorageService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ClienteController is a REST controller class that handles API endpoints for managing clients.
 * It is mapped to the specified API version and allows cross-origin requests from "http://localhost:4200".
 */
@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    public final StorageService storageService;
    public final ClienteService clienteService;

    /**
     * Constructs a new instance of ClienteController with the specified dependencies.
     *
     * @param storageService The StorageService used for file storage.
     * @param clienteService The ClienteService used for client-specific operations.
     */
    public ClienteController(StorageService storageService, ClienteService clienteService) {

        this.storageService = storageService;
        this.clienteService = clienteService;
    }

    /**
     * Saves a new client.
     *
     * @param cliente The Cliente object to be saved.
     */
    @PostMapping("/clientes")
    public void save(@RequestBody Cliente cliente) {
        clienteService.save(cliente);
    }

    /**
     * Retrieves all clients.
     *
     * @return A list of all Cliente objects.
     */
    @Transactional(readOnly = true)
    @GetMapping("/clientes")
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    /**
     * Retrieves a client by ID.
     *
     * @param id The ID of the client to retrieve.
     * @return An Optional containing the Cliente object, or an empty Optional if not found.
     */
    @Transactional(readOnly = true)
    @GetMapping("/clientes/{id}")
    public Optional<Cliente> findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    /**
     * Retrieves all solicitudes for a specific client.
     *
     * @param id The ID of the client.
     * @return A list of Solicitud objects belonging to the client.
     */
    @Transactional(readOnly = true)
    @GetMapping("/clientes/solicitudes/{id}")
    public List<Solicitud> findAllSolicitudes(@PathVariable Long id) {
        return clienteService.findAllSolcitudes(id);
    }

}
