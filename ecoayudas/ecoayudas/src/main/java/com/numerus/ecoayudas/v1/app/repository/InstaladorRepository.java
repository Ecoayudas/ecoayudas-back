package com.numerus.ecoayudas.v1.app.repository;


import com.numerus.ecoayudas.v1.app.model.Instalador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstaladorRepository extends JpaRepository<Instalador, Long> {


    Optional<Instalador> findOneByUsername(String username);
    Optional<Instalador> findOneByDni(String dni);
}
