package com.numerus.ecoayudas.v1.app.repository;

import com.numerus.ecoayudas.v1.app.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * ClienteRepository is a Spring Data JPA repository interface for managing Cliente entities.
 * It extends the JpaRepository interface, providing CRUD operations and additional querying capabilities.
 */
public interface ClienteRespository extends JpaRepository<Cliente, Long> {

    /**
     * Retrieves a Cliente entity by the specified dni.
     *
     * @param dni The dni of the Cliente.
     * @return An Optional containing the Cliente entity if found, or an empty Optional if not found.
     */
    Optional<Cliente> findOneByDni(String dni);
}
