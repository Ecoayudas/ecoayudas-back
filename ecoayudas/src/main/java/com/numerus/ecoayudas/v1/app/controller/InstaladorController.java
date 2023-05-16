package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.impl.InstaladorServiceImpl;
import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Instalador;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.service.StorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.version}")
public class InstaladorController {
    public final InstaladorServiceImpl instaladorServiceImpl;
    public final StorageService storageService;

    public InstaladorController(InstaladorServiceImpl instaladorServiceImpl, StorageService storageService) {
        this.instaladorServiceImpl = instaladorServiceImpl;
        this.storageService = storageService;
    }
    @PostMapping("/instaladores")
    public void save(@RequestBody Instalador instalador){instaladorServiceImpl.save(instalador);}
    @GetMapping("/instaladores")
    public List<Instalador> findAll(){return instaladorServiceImpl.findAll();}
    @GetMapping("/instaladores/{id}")
    public Optional<Instalador> findById(@PathVariable Long id){return instaladorServiceImpl.findById(id);}
    @GetMapping List<Cliente> findAllClientes(@PathVariable Long id){return instaladorServiceImpl.findAllClientes(id);}
    @GetMapping("/instaladores/solicitudes")
    public List<Solicitud> findAllSolicitudes(@PathVariable Long id){return instaladorServiceImpl.findAllSolcitudes(id);}
    @PostMapping("/instaladores/upload")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile){
        String path= storageService.store(multipartFile);
    }
}
