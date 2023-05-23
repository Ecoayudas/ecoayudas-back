package com.numerus.ecoayudas.v1.app.security.authorization;

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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

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
