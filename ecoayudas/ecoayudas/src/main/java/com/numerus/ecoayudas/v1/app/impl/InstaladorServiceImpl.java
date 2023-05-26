package com.numerus.ecoayudas.v1.app.impl;

import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Instalador;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.repository.InstaladorRepository;
import com.numerus.ecoayudas.v1.app.service.InstaladorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstaladorServiceImpl implements InstaladorService {
    private final InstaladorRepository instaladorRepository;
    private  final ClienteServiceImpl clienteServiceImpl;
    private final SolicitudServiceImpl solicitudServiceImpl;

    public InstaladorServiceImpl(InstaladorRepository instaladorRepository, ClienteServiceImpl clienteServiceImpl, SolicitudServiceImpl solicitudServiceImpl) {
        this.instaladorRepository = instaladorRepository;
        this.clienteServiceImpl = clienteServiceImpl;
        this.solicitudServiceImpl = solicitudServiceImpl;
    }
    public void save(Instalador instalador){instaladorRepository.save(instalador);}
    public List<Instalador> findAll(){return instaladorRepository.findAll();}
    public Optional<Instalador> findById(Long id){return instaladorRepository.findById(id);}
    public void deleteById(Long id){instaladorRepository.deleteById(id);}
    public Page<Instalador> instaladorPage(Pageable pageable){return  instaladorRepository.findAll(pageable);}
   public void crearCliente(Long id, Cliente cliente){

        Instalador instalador=instaladorRepository.findById(id).get();

        if (instalador.getId().equals(id)){
            cliente.setInstalador(instalador);
            instalador.addCliente(cliente);
            clienteServiceImpl.save(cliente);
            instaladorRepository.save(instalador);

        }else {
            throw new IllegalArgumentException("El ID especificado no corresponde a ningún Instalador.");
        }
    }
    public void crearSolicitud(Long id, Solicitud solicitud){
        Instalador instalador=instaladorRepository.findById(id).get();

        if (instalador.getId().equals(id)){
            solicitud.setInstalador(instalador);
            instalador.addSolicitud(solicitud);
            solicitudServiceImpl.save(solicitud);
            instaladorRepository.save(instalador);


        }else {
            throw new IllegalArgumentException("El ID especificado no corresponde a ningún Instalador.");
        }
    }
    public List<Cliente> findAllClientes(Long id){
        Instalador instalador=instaladorRepository.findById(id).get();
        if (instalador.getId().equals(id)){
            return instalador.getClientes();

        }else throw new IllegalArgumentException("El ID especificado no corresponde a ningún Instalador.");

    }
    public List<Solicitud> findAllSolcitudes(Long id){
        Instalador instalador=instaladorRepository.findById(id).get();
        if (instalador.getId().equals(id)){
            return instalador.getSolicitudes();

        }else throw new IllegalArgumentException("El ID especificado no corresponde a ningún Instalador.");

    }


}
