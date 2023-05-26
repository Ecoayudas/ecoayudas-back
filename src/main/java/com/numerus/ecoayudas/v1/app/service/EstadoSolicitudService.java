package com.numerus.ecoayudas.v1.app.service;

import com.numerus.ecoayudas.v1.app.model.EstadoSolicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This interface provides methods CRUD for EstadoSolicitud
 */
@Service
public interface EstadoSolicitudService {
    /**
     * Saves an EstadoSolicitud object.
     *
     * @param estadoSolicitud The EstadoSolicitud object to be saved.
     */
    public void save(EstadoSolicitud estadoSolicitud);

    /**
     * Retrieves all EstadoSolicitud objects.
     *
     * @return A list of EstadoSolicitud objects.
     */
    public List<EstadoSolicitud> findAll();

    /**
     * Retrieves an EstadoSolicitud object by its ID.
     *
     * @param id The ID of the EstadoSolicitud object.
     * @return An Optional containing the EstadoSolicitud object, if found.
     */
    public Optional<EstadoSolicitud> findById(Long id);

    /**
     * Deletes an EstadoSolicitud object by its ID.
     *
     * @param id The ID of the EstadoSolicitud object to be deleted.
     */
    public void deleteById(Long id);

    /**
     * Retrieves a paginated list of EstadoSolicitud objects.
     *
     * @param pageable The pagination information.
     * @return A Page containing the EstadoSolicitud objects.
     */
    public Page<EstadoSolicitud> estadoSolicitudPage(Pageable pageable);

}
