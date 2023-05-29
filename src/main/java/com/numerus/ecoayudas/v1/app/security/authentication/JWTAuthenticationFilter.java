package com.numerus.ecoayudas.v1.app.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.numerus.ecoayudas.v1.app.dto.UserDto;
import com.numerus.ecoayudas.v1.app.security.utils.JWTUtils;
import com.numerus.ecoayudas.v1.app.security.constants.SecurityConstants;
import com.numerus.ecoayudas.v1.app.security.authentication.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * JWTAuthenticationFilter is responsible for handling the authentication process for username/password-based authentication.
 * It extends UsernamePasswordAuthenticationFilter.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    /**
     * Attempts the authentication process based on the provided username and password.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @return Authentication object representing the authenticated user
     * @throws AuthenticationException if the authentication process fails
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(SecurityConstants.METHOD_POST)) {
            throw new AuthenticationServiceException(request.getMethod());
        } else {
            UserDto authCredentials = new UserDto();

            try {
                authCredentials = new ObjectMapper().readValue(request.getReader(), UserDto.class);
            } catch (IOException e) {
                return null;
            }
            List<GrantedAuthority> grantedAuthorities =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authCredentials.getRole());
            UsernamePasswordAuthenticationToken userNamePAT = new UsernamePasswordAuthenticationToken(

                    authCredentials.getDni(),
                    authCredentials.getPassword(),
                    grantedAuthorities

            );
            return getAuthenticationManager().authenticate(userNamePAT);
        }
    }

    /**
     * Handles the successful authentication and generates a JWT token.
     *
     * @param request    HttpServletRequest object
     * @param response   HttpServletResponse object
     * @param chain      FilterChain object
     * @param authResult Authentication object representing the authenticated user
     * @throws IOException      if an I/O error occurs during the handling process
     * @throws ServletException if an error occurs during the handling process
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authResult.getPrincipal();
        String token = JWTUtils.createToken(userDetailsImpl.getId(), userDetailsImpl.getDni(), String.valueOf(userDetailsImpl.getAuthorities()));

        PrintWriter writer = response.getWriter();
        response.setContentType(SecurityConstants.APP_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.put(SecurityConstants.TOKEN_JSON, token);
        String jsonResponse = jsonObject.toString();
        writer.write(jsonResponse);
        writer.flush();
    }
}
