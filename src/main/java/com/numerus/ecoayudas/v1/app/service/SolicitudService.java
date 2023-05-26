package com.numerus.ecoayudas.v1.app.service;

import com.numerus.ecoayudas.v1.app.model.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This interface provides methods CRUD for Solicitud
 */
@Service
public interface SolicitudService {
    /**
     * Saves a Solicitud object.
     *
     * @param solicitud The Solicitud object to be saved.
     */
    public void save(Solicitud solicitud);

    /**
     * Retrieves all Solicitud objects.
     *
     * @return A list of Solicitud objects.
     */
    public List<Solicitud> findAll();

    /**
     * Retrieves a Solicitud object by its ID.
     *
     * @param id The ID of the Solicitud object.
     * @return An Optional containing the Solicitud object, if found.
     */
    public Optional<Solicitud> findById(Long id);

    /**
     * Deletes a Solicitud object by its ID.
     *
     * @param id The ID of the Solicitud object to be deleted.
     */
    public void deleteById(Long id);

    /**
     * Retrieves a paginated list of Solicitud objects.
     *
     * @param pageable The pagination information.
     * @return A Page containing the Solicitud objects.
     */
    public Page<Solicitud> solicitudPage(Pageable pageable);


}
