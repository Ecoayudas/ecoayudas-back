package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.model.EstadoSolicitud;
import com.numerus.ecoayudas.v1.app.impl.EstadoSolicitudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("${api.version}")
public class EstadoSolicitudController {
    public  final EstadoSolicitudServiceImpl estadoSolicitudServiceImpl;

    public EstadoSolicitudController(EstadoSolicitudServiceImpl estadoSolicitudServiceImpl) {
        this.estadoSolicitudServiceImpl = estadoSolicitudServiceImpl;
    }


    @PostMapping("/estados")
    public void save(@RequestBody EstadoSolicitud estado){estadoSolicitudServiceImpl.save(estado);}
    @GetMapping("/estados")
    public List<EstadoSolicitud> findAll(){return estadoSolicitudServiceImpl.findAll();}
    @GetMapping("/estados/{id}")
    public EstadoSolicitud findById(@PathVariable Long id){ return estadoSolicitudServiceImpl.findById(id).orElse(null);}
    @DeleteMapping("/estados/{id}")
    public void delete(@PathVariable Long id){estadoSolicitudServiceImpl.deleteById(id);}
    @GetMapping("/estado")
    public List<EstadoSolicitud> estadoSolicitudPage(@RequestParam int page, @RequestParam int size){
        Pageable pageable= PageRequest.of(page, size);
        Page<EstadoSolicitud> estadoSolicitudPage=estadoSolicitudServiceImpl.estadoSolicitudPage(pageable);
        return estadoSolicitudPage.getContent();
    }
}
