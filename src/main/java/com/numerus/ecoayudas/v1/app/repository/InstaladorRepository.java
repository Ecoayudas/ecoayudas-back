package com.numerus.ecoayudas.v1.app.repository;


import com.numerus.ecoayudas.v1.app.model.Instalador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * InstaladorRepository is a Spring Data JPA repository interface for managing Instalador entities.
 * It extends the JpaRepository interface, providing CRUD operations and additional querying capabilities.
 */
public interface InstaladorRepository extends JpaRepository<Instalador, Long> {

    /**
     * Retrieves an Optional of Instalador by the given DNI (identification number).
     *
     * @param dni the DNI to search for
     * @return an Optional containing the Instalador, or an empty Optional if not found
     */
    Optional<Instalador> findOneByDni(String dni);
}
