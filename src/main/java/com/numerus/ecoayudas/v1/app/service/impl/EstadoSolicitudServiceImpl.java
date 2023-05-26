package com.numerus.ecoayudas.v1.app.service.impl;

import com.numerus.ecoayudas.v1.app.model.EstadoSolicitud;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.repository.EstadoSolicitudRepository;
import com.numerus.ecoayudas.v1.app.service.EstadoSolicitudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class implements EstadoSolicitudService interface
 */
@Service
public class EstadoSolicitudServiceImpl implements EstadoSolicitudService {

    private final EstadoSolicitudRepository estadoSolicitudRepository;


    /**
     * Constructs a new EstadoSolicitudServiceImpl with the given EstadoSolicitudRepository.
     *
     * @param estadoSolicitudRepository The EstadoSolicitudRepository used for data access.
     */
    public EstadoSolicitudServiceImpl(EstadoSolicitudRepository estadoSolicitudRepository) {
        this.estadoSolicitudRepository = estadoSolicitudRepository;
    }

    /**
     * Saves an EstadoSolicitud object.
     *
     * @param estadoSolicitud The EstadoSolicitud object to be saved.
     */
    public void save(EstadoSolicitud estadoSolicitud) {
        estadoSolicitudRepository.save(estadoSolicitud);
    }

    /**
     * Retrieves all EstadoSolicitud objects.
     *
     * @return A list of EstadoSolicitud objects.
     */
    public List<EstadoSolicitud> findAll() {
        return estadoSolicitudRepository.findAll();
    }

    /**
     * Retrieves an EstadoSolicitud object by its ID.
     *
     * @param id The ID of the EstadoSolicitud object.
     * @return An Optional containing the EstadoSolicitud object, if found.
     */
    public Optional<EstadoSolicitud> findById(Long id) {
        return estadoSolicitudRepository.findById(id);
    }

    /**
     * Deletes an EstadoSolicitud object by its ID.
     *
     * @param id The ID of the EstadoSolicitud object to be deleted.
     */
    public void deleteById(Long id) {
        estadoSolicitudRepository.deleteById(id);
    }

    /**
     * Retrieves a paginated list of EstadoSolicitud objects.
     *
     * @param pageable The pagination information.
     * @return A Page containing the EstadoSolicitud objects.
     */
    public Page<EstadoSolicitud> estadoSolicitudPage(Pageable pageable) {
        return estadoSolicitudRepository.findAll(pageable);
    }
}
