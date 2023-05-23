package com.numerus.ecoayudas.v1.app.impl;

import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Instalador;
import com.numerus.ecoayudas.v1.app.model.Solicitud;
import com.numerus.ecoayudas.v1.app.repository.ClienteRespository;
import com.numerus.ecoayudas.v1.app.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRespository clienteRespository;

    public ClienteServiceImpl(ClienteRespository clienteRespository) {
        this.clienteRespository = clienteRespository;
    }

    public void save(Cliente cliente){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password= passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(password);
        clienteRespository.save(cliente);}
    public List<Cliente> findAll(){return  clienteRespository.findAll();}
    public Optional<Cliente> findById(Long id){return clienteRespository.findById(id);}
    public void deleteById(Long id){clienteRespository.deleteById(id);}
    public Page<Cliente> clientePage(Pageable pageable){return clienteRespository.findAll(pageable);}
    public List<Solicitud> findAllSolcitudes(Long id){
        Cliente cliente =clienteRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("Empty"));

        if (cliente.getId().equals(id)){
            return cliente.getSolicitudes();

        }else throw new IllegalArgumentException("El ID especificado no corresponde a ningún Instalador.");

    }
}
