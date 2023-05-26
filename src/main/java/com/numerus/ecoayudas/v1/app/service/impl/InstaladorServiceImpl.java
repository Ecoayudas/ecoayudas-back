package com.numerus.ecoayudas.v1.app.service.impl;

import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Instalador;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.repository.InstaladorRepository;
import com.numerus.ecoayudas.v1.app.service.InstaladorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class implements InstaladorService interface
 */
@Service
public class InstaladorServiceImpl implements InstaladorService {
    private final InstaladorRepository instaladorRepository;
    private final ClienteServiceImpl clienteServiceImpl;
    private final SolicitudServiceImpl solicitudServiceImpl;

    /**
     * Constructs a new InstaladorServiceImpl with the specified dependencies.
     *
     * @param instaladorRepository   The InstaladorRepository for data access.
     * @param clienteServiceImpl     The ClienteServiceImpl for managing Cliente objects.
     * @param solicitudServiceImpl   The SolicitudServiceImpl for managing Solicitud objects.
     */
    public InstaladorServiceImpl(InstaladorRepository instaladorRepository, ClienteServiceImpl clienteServiceImpl, SolicitudServiceImpl solicitudServiceImpl) {
        this.instaladorRepository = instaladorRepository;
        this.clienteServiceImpl = clienteServiceImpl;
        this.solicitudServiceImpl = solicitudServiceImpl;
    }

    /**
     * Saves an Instalador object.
     *
     * @param instalador The Instalador object to be saved.
     */
    public void save(Instalador instalador) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(instalador.getPassword());
        instalador.setPassword(password);
        instaladorRepository.save(instalador);
    }

    /**
     * Retrieves all Instalador objects.
     *
     * @return A list of Instalador objects.
     */
    public List<Instalador> findAll() {
        return instaladorRepository.findAll();
    }

    /**
     * Retrieves an Instalador object by its ID.
     *
     * @param id The ID of the Instalador object.
     * @return An Optional containing the Instalador object, if found.
     */
    public Optional<Instalador> findById(Long id) {
        return instaladorRepository.findById(id);
    }

    /**
     * Deletes an Instalador object by its ID.
     *
     * @param id The ID of the Instalador object to be deleted.
     */
    public void deleteById(Long id) {
        instaladorRepository.deleteById(id);
    }

    /**
     * Retrieves a paginated list of Instalador objects.
     *
     * @param pageable The pagination information.
     * @return A Page containing the Instalador objects.
     */
    public Page<Instalador> instaladorPage(Pageable pageable) {
        return instaladorRepository.findAll(pageable);
    }

    /**
     * Creates a new Cliente associated with the specified Instalador ID.
     *
     * @param id      The ID of the Instalador.
     * @param cliente The Cliente object to be created.
     * @throws IllegalArgumentException if the specified ID does not correspond to any Instalador.
     */
    public void crearCliente(Long id, Cliente cliente) {
        Instalador instalador = instaladorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        if (instalador.getId().equals(id)) {
            cliente.setInstalador(instalador);
            instalador.addCliente(cliente);
            clienteServiceImpl.save(cliente);
            instaladorRepository.save(instalador);
        } else {
            throw new IllegalArgumentException("The specified ID does not correspond to any Instalador.");
        }
    }

    /**
     * Creates a new Solicitud associated with the specified Instalador ID.
     *
     * @param id        The ID of the Instalador.
     * @param solicitud The Solicitud object to be created.
     * @throws IllegalArgumentException if the specified ID does not correspond to any Instalador.
     */
    public void crearSolicitud(Long id, Solicitud solicitud) {
        Instalador instalador = instaladorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        if (instalador.getId().equals(id)) {
            solicitud.setInstalador(instalador);
            instalador.addSolicitud(solicitud);
            solicitudServiceImpl.save(solicitud);
            instaladorRepository.save(instalador);
        } else {
            throw new IllegalArgumentException("The specified ID does not correspond to any Instalador.");
        }
    }

    /**
     * Retrieves all Cliente objects associated with the specified Instalador ID.
     *
     * @param id The ID of the Instalador.
     * @return A list of Cliente objects.
     * @throws IllegalArgumentException if the specified ID does not correspond to any Instalador.
     */
    public List<Cliente> findAllClientes(Long id) {
        Instalador instalador = instaladorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        if (instalador.getId().equals(id)) {
            return instalador.getClientes();
        } else {
            throw new IllegalArgumentException("The specified ID does not correspond to any Instalador.");
        }
    }

    /**
     * Retrieves all Solicitud objects associated with the specified Instalador ID.
     *
     * @param id The ID of the Instalador.
     * @return A list of Solicitud objects.
     * @throws IllegalArgumentException if the specified ID does not correspond to any Instalador.
     */
    public List<Solicitud> findAllSolcitudes(Long id) {
        Instalador instalador = instaladorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        if (instalador.getId().equals(id)) {
            return instalador.getSolicitudes();
        } else {
            throw new IllegalArgumentException("The specified ID does not correspond to any Instalador.");
        }
    }
}





