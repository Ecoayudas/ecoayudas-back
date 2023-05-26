package com.numerus.ecoayudas.v1.app.service;

import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This interface provides methods CRUD for Cliente
 */

@Service
public interface ClienteService {
    /**
     * Saves a Customer object to the database.
     *
     * @param cliente The Customer object to be saved.
     */
    public void save(Cliente cliente);

    /**
     * Retrieves all Customer objects from the database.
     *
     * @return A list of Customer objects.
     */
    public List<Cliente> findAll();

    /**
     * Retrieves a Customer object from the database by its ID.
     *
     * @param id The ID of the Customer object.
     * @return An Optional containing the Customer object, if found.
     */
    public Optional<Cliente> findById(Long id);

    /**
     * Deletes a Customer object from the database by its ID.
     *
     * @param id The ID of the Customer object to be deleted.
     */
    public void deleteById(Long id);

    /**
     * Retrieves a paginated list of Customer objects from the database.
     *
     * @param pageable The pagination information.
     * @return A Page containing the Customer objects.
     */
    public Page<Cliente> clientePage(Pageable pageable);

    /**
     * Retrieves all Solicitud objects associated with a given ID.
     *
     * @param id The ID of the Customer.
     * @return A list of Solicitud objects.
     */
    public List<Solicitud> findAllSolcitudes(Long id);
}
