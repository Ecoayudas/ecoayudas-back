package com.numerus.ecoayudas.v1.app.security;


import com.numerus.ecoayudas.v1.app.dto.UserDto;
import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Instalador;
import com.numerus.ecoayudas.v1.app.repository.ClienteRespository;
import com.numerus.ecoayudas.v1.app.repository.InstaladorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClienteRespository clienteRespository;
    private final InstaladorRepository instaladorRepository;

    public UserDetailsServiceImpl(ClienteRespository clienteRespository, InstaladorRepository instaladorRepository) {
        this.clienteRespository = clienteRespository;
        this.instaladorRepository = instaladorRepository;
    }


 @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Cliente cliente = clienteRespository.findOneByDni(dni).orElse(null);
        Instalador instalador = instaladorRepository.findOneByDni(dni).orElse(null);
        if (cliente != null) {
            UserDto userDto = new UserDto();
            userDto.setDni(cliente.getDni());
            userDto.setPassword(cliente.getPassword());
            userDto.setRole(cliente.getRole());
            return new UserDetailsImpl(userDto);
        } else if (instalador != null) {

            UserDto userDto = new UserDto();
            userDto.setDni(instalador.getDni());
            userDto.setPassword(instalador.getPassword());
            userDto.setRole(instalador.getRole());
            return new UserDetailsImpl(userDto);
        } else return null;
    }

}

