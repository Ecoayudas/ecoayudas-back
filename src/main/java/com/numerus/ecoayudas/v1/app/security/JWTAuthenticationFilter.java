package com.numerus.ecoayudas.v1.app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numerus.ecoayudas.v1.app.dto.UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.List;
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        UserDto authCredentials = new UserDto();


        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), UserDto.class);
        } catch (IOException e) {
            return null;
        }
        List<GrantedAuthority> grantedAuthorities=
                AuthorityUtils.commaSeparatedStringToAuthorityList(authCredentials.getRole());
        UsernamePasswordAuthenticationToken userNamePAT = new UsernamePasswordAuthenticationToken(

                authCredentials.getDni(),
                authCredentials.getPassword(),
                grantedAuthorities

        );
        return getAuthenticationManager().authenticate(userNamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authResult.getPrincipal();

        String token = JWTUtil.createToken(userDetailsImpl.getDni(), String.valueOf(userDetailsImpl.getAuthorities()));
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().close();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
