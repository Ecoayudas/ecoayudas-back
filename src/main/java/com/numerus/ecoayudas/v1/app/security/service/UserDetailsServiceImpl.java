package com.numerus.ecoayudas.v1.app.security.service;


import com.numerus.ecoayudas.v1.app.dto.UserDto;
import com.numerus.ecoayudas.v1.app.model.Cliente;
import com.numerus.ecoayudas.v1.app.model.Instalador;
import com.numerus.ecoayudas.v1.app.repository.ClienteRespository;
import com.numerus.ecoayudas.v1.app.repository.InstaladorRepository;
import com.numerus.ecoayudas.v1.app.security.authentication.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class implements UserDetailsService interface
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClienteRespository clienteRespository;
    private final InstaladorRepository instaladorRepository;

    public UserDetailsServiceImpl(ClienteRespository clienteRespository, InstaladorRepository instaladorRepository) {
        this.clienteRespository = clienteRespository;
        this.instaladorRepository = instaladorRepository;
    }

    /**
     * Loads the user details based on the provided username (DNI).
     *
     * @param dni The username (DNI) of the user.
     * @return An instance of UserDetails representing the user details.
     * @throws UsernameNotFoundException If the user with the provided username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Cliente cliente = clienteRespository.findOneByDni(dni).orElse(null);
        Instalador instalador = instaladorRepository.findOneByDni(dni).orElse(null);

        if (cliente != null) {
            UserDto userDto = new UserDto();
            userDto.setId(cliente.getId());
            userDto.setDni(cliente.getDni());
            userDto.setPassword(cliente.getPassword());
            userDto.setRole(cliente.getRole());
            return new UserDetailsImpl(userDto);
        } else if (instalador != null) {
            UserDto userDto = new UserDto();
            userDto.setId(instalador.getId());
            userDto.setDni(instalador.getDni());
            userDto.setPassword(instalador.getPassword());
            userDto.setRole(instalador.getRole());
            return new UserDetailsImpl(userDto);
        } else {
            throw new UsernameNotFoundException("User with DNI '" + dni + "' not found.");
        }
    }
}


