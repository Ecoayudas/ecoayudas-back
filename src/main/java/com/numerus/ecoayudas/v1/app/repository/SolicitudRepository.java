package com.numerus.ecoayudas.v1.app.repository;

import com.numerus.ecoayudas.v1.app.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * SolicitudRepository is a Spring Data JPA repository interface for managing Solicitud entities.
 * It extends the JpaRepository interface, providing CRUD operations and additional querying capabilities.
 */
public interface SolicitudRepository extends JpaRepository <Solicitud, Long> {
}
