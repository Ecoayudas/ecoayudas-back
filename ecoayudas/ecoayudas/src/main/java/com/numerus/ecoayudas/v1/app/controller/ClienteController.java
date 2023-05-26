package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.impl.ClienteServiceImpl;
import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.service.StorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.version}")
public class ClienteController {
    public final ClienteServiceImpl clienteServiceImpl;
    public final StorageService storageService;

    public ClienteController(ClienteServiceImpl clienteServiceImpl, StorageService storageService) {
        this.clienteServiceImpl = clienteServiceImpl;
        this.storageService = storageService;
    }

    @PostMapping("/clientes")
    public void save(@RequestBody Cliente cliente){clienteServiceImpl.save(cliente);}
    @GetMapping("/clientes")
    public List<Cliente> findAll(){return clienteServiceImpl.findAll();}
    @GetMapping("/clientes/{id}")
    public Optional<Cliente> findById(@PathVariable Long id){return clienteServiceImpl.findById(id);}
    @GetMapping("/clientes/solicitudes")
    public List<Solicitud> findAllSolicitudes(@PathVariable Long id){return clienteServiceImpl.findAllSolcitudes(id);}
    @PostMapping("/clientes/upload")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile){
        String path= storageService.store(multipartFile);
    }
}
