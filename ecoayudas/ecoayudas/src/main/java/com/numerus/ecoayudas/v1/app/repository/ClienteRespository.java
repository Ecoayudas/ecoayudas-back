package com.numerus.ecoayudas.v1.app.repository;

import com.numerus.ecoayudas.v1.app.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRespository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findOneByUsername(String username);
    Optional<Cliente> findOneByDni(String dni);
}
