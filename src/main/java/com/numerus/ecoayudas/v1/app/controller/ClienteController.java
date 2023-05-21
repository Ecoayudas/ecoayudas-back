package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.impl.ClienteServiceImpl;
import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.service.ClienteService;
import com.numerus.ecoayudas.v1.app.service.StorageService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {
    public final ClienteServiceImpl clienteServiceImpl;
    public final StorageService storageService;
    public final ClienteService clienteService;

    public ClienteController(ClienteServiceImpl clienteServiceImpl, StorageService storageService, ClienteService clienteService) {
        this.clienteServiceImpl = clienteServiceImpl;
        this.storageService = storageService;
        this.clienteService = clienteService;
    }

    @PostMapping("/clientes")
    public void save(@RequestBody Cliente cliente){clienteServiceImpl.save(cliente);}
    @Transactional(readOnly = true)
    @GetMapping("/clientes")
    public List<Cliente> findAll(){return clienteServiceImpl.findAll();}
    @Transactional(readOnly = true)
    @GetMapping("/clientes/{id}")
    public Optional<Cliente> findById(@PathVariable Long id){return clienteServiceImpl.findById(id);}
    @Transactional(readOnly = true)
    @GetMapping("/clientes/solicitudes/{id}")
    public List<Solicitud> findAllSolicitudes(@PathVariable Long id){return clienteServiceImpl.findAllSolcitudes(id);}

}
