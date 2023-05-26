package com.numerus.ecoayudas.v1.app.service;

import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Instalador;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This interface provides methods CRUD for Instalador
 */
@Service
public interface InstaladorService {

    /**
     * Saves an Instalador object.
     *
     * @param instalador The Instalador object to be saved.
     */
    public void save(Instalador instalador);

    /**
     * Retrieves all Instalador objects.
     *
     * @return A list of Instalador objects.
     */
    public List<Instalador> findAll();

    /**
     * Retrieves an Instalador object by its ID.
     *
     * @param id The ID of the Instalador object.
     * @return An Optional containing the Instalador object, if found.
     */
    public Optional<Instalador> findById(Long id);

    /**
     * Deletes an Instalador object by its ID.
     *
     * @param id The ID of the Instalador object to be deleted.
     */
    public void deleteById(Long id);

    /**
     * Retrieves a paginated list of Instalador objects.
     *
     * @param pageable The pagination information.
     * @return A Page containing the Instalador objects.
     */
    public Page<Instalador> instaladorPage(Pageable pageable);

    /**
     * Creates a new Cliente associated with the specified Instalador ID.
     *
     * @param id      The ID of the Instalador.
     * @param cliente The Cliente object to be created.
     */
    public void crearCliente(Long id, Cliente cliente);

    /**
     * Creates a new Solicitud associated with the specified Instalador ID.
     *
     * @param id        The ID of the Instalador.
     * @param solicitud The Solicitud object to be created.
     */
    public void crearSolicitud(Long id, Solicitud solicitud);

    /**
     * Retrieves all Cliente objects associated with the specified Instalador ID.
     *
     * @param id The ID of the Instalador.
     * @return A list of Cliente objects.
     */
    public List<Cliente> findAllClientes(Long id);

    /**
     * Retrieves all Solicitud objects associated with the specified Instalador ID.
     *
     * @param id The ID of the Instalador.
     * @return A list of Solicitud objects.
     */
    public List<Solicitud> findAllSolcitudes(Long id);


}
