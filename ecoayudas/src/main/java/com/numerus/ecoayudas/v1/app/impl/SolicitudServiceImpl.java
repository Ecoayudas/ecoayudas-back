package com.numerus.ecoayudas.v1.app.impl;


import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.repository.SolicitudRepository;
import com.numerus.ecoayudas.v1.app.service.SolicitudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }
    public void save(Solicitud solicitud){solicitudRepository.save(solicitud);}
    public List<Solicitud> findAll(){return  solicitudRepository.findAll();}
    public Optional<Solicitud> findById(Long id){return solicitudRepository.findById(id);}
    public void deleteById(Long id){solicitudRepository.deleteById(id);}
    public Page<Solicitud> solicitudPage(Pageable pageable){return solicitudRepository.findAll(pageable);}
}
