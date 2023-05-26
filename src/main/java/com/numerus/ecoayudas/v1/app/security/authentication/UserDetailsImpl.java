package com.numerus.ecoayudas.v1.app.security.authentication;


import com.numerus.ecoayudas.v1.app.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class implements UserDetails interface
 */
public class UserDetailsImpl implements UserDetails {
    private final transient UserDto user;

    /**
     * Constructs a new UserDetailsImpl instance with the specified UserDto.
     *
     * @param user the UserDto containing the user details.
     */
    public UserDetailsImpl(UserDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Returns the DNI (Documento Nacional de Identidad) of the user.
     *
     * @return the DNI of the user.
     */
    public String getDni() {
        return user.getDni();
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user.
     */
    public Long getId() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
