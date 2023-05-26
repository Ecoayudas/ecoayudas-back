package com.numerus.ecoayudas.v1.app.service.impl;


import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.repository.SolicitudRepository;
import com.numerus.ecoayudas.v1.app.service.SolicitudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class implements SolicitudService interface
 */
@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;

    /**
     * Constructs a new SolicitudServiceImpl with the given SolicitudRepository.
     *
     * @param solicitudRepository The SolicitudRepository used for data access.
     */
    public SolicitudServiceImpl(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    /**
     * Saves a Solicitud object.
     *
     * @param solicitud The Solicitud object to be saved.
     */
    public void save(Solicitud solicitud) {
        solicitudRepository.save(solicitud);
    }

    /**
     * Retrieves all Solicitud objects.
     *
     * @return A list of Solicitud objects.
     */
    public List<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }

    /**
     * Retrieves a Solicitud object by its ID.
     *
     * @param id The ID of the Solicitud object.
     * @return An Optional containing the Solicitud object, if found.
     */
    public Optional<Solicitud> findById(Long id) {
        return solicitudRepository.findById(id);
    }

    /**
     * Deletes a Solicitud object by its ID.
     *
     * @param id The ID of the Solicitud object to be deleted.
     */
    public void deleteById(Long id) {
        solicitudRepository.deleteById(id);
    }

    /**
     * Retrieves a paginated list of Solicitud objects.
     *
     * @param pageable The pagination information.
     * @return A Page containing the Solicitud objects.
     */
    public Page<Solicitud> solicitudPage(Pageable pageable) {
        return solicitudRepository.findAll(pageable);
    }
}
