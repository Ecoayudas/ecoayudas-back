package com.numerus.ecoayudas.v1.app.repository;

import com.numerus.ecoayudas.v1.app.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository <Solicitud, Long> {
}
