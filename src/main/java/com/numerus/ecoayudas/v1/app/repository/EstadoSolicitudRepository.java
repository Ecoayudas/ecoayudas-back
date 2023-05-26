package com.numerus.ecoayudas.v1.app.repository;

import com.numerus.ecoayudas.v1.app.model.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * EstadoSolicitudRepository is a Spring Data JPA repository interface for managing EstadoSolicitud entities.
 * It extends the JpaRepository interface, providing CRUD operations and additional querying capabilities.
 */
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitud, Long> {
}
