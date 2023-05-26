package com.numerus.ecoayudas.v1.app.service.impl;

import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.repository.ClienteRespository;
import com.numerus.ecoayudas.v1.app.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class implements ClienteService
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRespository clienteRespository;

    /**
     * Constructs a new ClienteServiceImpl with the specified ClienteRespository.
     *
     * @param clienteRespository The ClienteRespository to be used for data access.
     */
    public ClienteServiceImpl(ClienteRespository clienteRespository) {
        this.clienteRespository = clienteRespository;
    }

    /**
     * Saves a Cliente object to the database.
     *
     * @param cliente The Cliente object to be saved.
     */
    public void save(Cliente cliente) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(password);
        clienteRespository.save(cliente);
    }

    /**
     * Retrieves all Cliente objects from the database.
     *
     * @return A list of Cliente objects.
     */
    public List<Cliente> findAll() {
        return clienteRespository.findAll();
    }

    /**
     * Retrieves a Cliente object from the database by its ID.
     *
     * @param id The ID of the Cliente object.
     * @return An Optional containing the Cliente object, if found.
     */
    public Optional<Cliente> findById(Long id) {
        return clienteRespository.findById(id);
    }

    /**
     * Deletes a Cliente object from the database by its ID.
     *
     * @param id The ID of the Cliente object to be deleted.
     */
    public void deleteById(Long id) {
        clienteRespository.deleteById(id);
    }

    /**
     * Retrieves a paginated list of Cliente objects from the database.
     *
     * @param pageable The pagination information.
     * @return A Page containing the Cliente objects.
     */
    public Page<Cliente> clientePage(Pageable pageable) {
        return clienteRespository.findAll(pageable);
    }

    /**
     * Retrieves all Solicitud objects associated with a given ID.
     *
     * @param id The ID of the Cliente.
     * @return A list of Solicitud objects.
     * @throws IllegalArgumentException if the specified ID does not correspond to any Cliente.
     */
    public List<Solicitud> findAllSolcitudes(Long id) {
        Cliente cliente = clienteRespository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empty"));

        if (cliente.getId().equals(id)) {
            return cliente.getSolicitudes();
        } else {
            throw new IllegalArgumentException("The specified ID does not correspond to any Cliente.");
        }
    }
}

