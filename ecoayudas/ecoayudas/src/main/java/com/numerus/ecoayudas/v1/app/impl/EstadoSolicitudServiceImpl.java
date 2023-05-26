package com.numerus.ecoayudas.v1.app.impl;

import com.numerus.ecoayudas.v1.app.model.EstadoSolicitud;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.repository.EstadoSolicitudRepository;
import com.numerus.ecoayudas.v1.app.service.EstadoSolicitudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoSolicitudServiceImpl implements EstadoSolicitudService {

    private final EstadoSolicitudRepository estadoSolicitudRepository;

    public EstadoSolicitudServiceImpl(EstadoSolicitudRepository estadoSolicitudRepository) {
        this.estadoSolicitudRepository = estadoSolicitudRepository;
    }
    public void save(EstadoSolicitud estadoSolicitud){estadoSolicitudRepository.save(estadoSolicitud);}
    public List<EstadoSolicitud> findAll(){return  estadoSolicitudRepository.findAll();}
    public Optional<EstadoSolicitud> findById(Long id){return estadoSolicitudRepository.findById(id);}
    public void deleteById(Long id){estadoSolicitudRepository.deleteById(id);}
    public Page<EstadoSolicitud> estadoSolicitudPage(Pageable pageable){return estadoSolicitudRepository.findAll(pageable);}
}
