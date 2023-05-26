package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.impl.SolicitudServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}")
public class SolicitudController {

    public  final SolicitudServiceImpl solicitudServiceImpl;

    public SolicitudController(SolicitudServiceImpl solicitudServiceImpl) {
        this.solicitudServiceImpl = solicitudServiceImpl;
    }
    @PostMapping("/solicitudes")
    public void save(@RequestBody Solicitud solicitud){solicitudServiceImpl.save(solicitud);}
    @GetMapping("/solicitudes")
    public List<Solicitud> findAll(){return solicitudServiceImpl.findAll();}
    @GetMapping("/solicitudes/{id}")
    public Solicitud findById(@PathVariable Long id){ return solicitudServiceImpl.findById(id).orElse(null);}
    @DeleteMapping("/solicitudes/{id}")
    public void delete(@PathVariable Long id){solicitudServiceImpl.deleteById(id);}
    @GetMapping("/solicitud")
    public List<Solicitud> solicitudPage(@RequestParam int page, @RequestParam int size){
        Pageable pageable= PageRequest.of(page, size);
        Page<Solicitud> solicitudPage=solicitudServiceImpl.solicitudPage(pageable);
        return solicitudPage.getContent();
    }
}
