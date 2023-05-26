package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.model.EstadoSolicitud;
import com.numerus.ecoayudas.v1.app.service.EstadoSolicitudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EstadoSolicitudController is a REST controller class that handles API endpoints for managing EstadoSolicitudController.
 * It is mapped to the specified API version and allows cross-origin requests from "http://localhost:4200".
 */
@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoSolicitudController {
    public final EstadoSolicitudService estadoSolicitudService;

    public EstadoSolicitudController(EstadoSolicitudService estadoSolicitudService) {
        this.estadoSolicitudService = estadoSolicitudService;
    }

    /**
     * Saves a new estado solicitud.
     *
     * @param estado The estado solicitud to save.
     */
    @PostMapping("/estados")
    public void save(@RequestBody EstadoSolicitud estado) {
        estadoSolicitudService.save(estado);
    }

    /**
     * Retrieves all estado solicitudes.
     *
     * @return A list of estado solicitudes.
     */
    @GetMapping("/estados")
    public List<EstadoSolicitud> findAll() {
        return estadoSolicitudService.findAll();
    }

    /**
     * Retrieves a estado solicitud by its ID.
     *
     * @param id The ID of the estado solicitud.
     * @return The estado solicitud with the specified ID, or null if not found.
     */
    @GetMapping("/estados/{id}")
    public EstadoSolicitud findById(@PathVariable Long id) {
        return estadoSolicitudService.findById(id).orElse(null);
    }

    /**
     * Deletes a estado solicitud by its ID.
     *
     * @param id The ID of the estado solicitud to delete.
     */
    @DeleteMapping(value = "/estados/{id}")
    public void delete(@PathVariable Long id) {
        estadoSolicitudService.deleteById(id);
    }

    /**
     * Retrieves a page of estado solicitudes.
     *
     * @param page The page number.
     * @param size The number of items per page.
     * @return A list of estado solicitudes in the specified page.
     */
    @GetMapping("/estado")
    public List<EstadoSolicitud> estadoSolicitudPage(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EstadoSolicitud> estadoSolicitudPage = estadoSolicitudService.estadoSolicitudPage(pageable);
        return estadoSolicitudPage.getContent();
    }
}
