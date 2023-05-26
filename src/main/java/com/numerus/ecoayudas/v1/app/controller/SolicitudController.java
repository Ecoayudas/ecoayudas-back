package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.service.SolicitudService;
import com.numerus.ecoayudas.v1.app.service.impl.SolicitudServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SolicitudController is a REST controller class that handles API endpoints for managing solicitudes.
 * It is mapped to the specified API version and allows cross-origin requests from "http://localhost:4200".
 */
@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class SolicitudController {

    public final SolicitudService solicitudService;

    /**
     * Constructor for SolicitudController.
     *
     * @param solicitudService The service for solicitud entities.
     */
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    /**
     * Save a solicitud entity.
     *
     * @param solicitud The solicitud entity to be saved.
     */
    @PostMapping("/solicitudes")
    public void save(@RequestBody Solicitud solicitud) {
        solicitudService.save(solicitud);
    }

    /**
     * Get a list of all solicitud entities.
     *
     * @return The list of solicitud entities.
     */
    @GetMapping("/solicitudes")
    public List<Solicitud> findAll() {
        return solicitudService.findAll();
    }

    /**
     * Get a solicitud entity by ID.
     *
     * @param id The ID of the solicitud entity.
     * @return The solicitud entity, or null if not found.
     */
    @GetMapping("/solicitudes/{id}")
    public Solicitud findById(@PathVariable Long id) {
        return solicitudService.findById(id).orElse(null);
    }

    /**
     * Delete a solicitud entity by ID.
     *
     * @param id The ID of the solicitud entity to be deleted.
     */
    @DeleteMapping("/solicitudes/{id}")
    public void delete(@PathVariable Long id) {
        solicitudService.deleteById(id);
    }

    /**
     * Get a paginated list of solicitud entities.
     *
     * @param page The page number.
     * @param size The page size.
     * @return The list of solicitud entities for the specified page.
     */
    @GetMapping("/solicitud")
    public List<Solicitud> solicitudPage(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Solicitud> solicitudPage = solicitudService.solicitudPage(pageable);
        return solicitudPage.getContent();
    }
}
