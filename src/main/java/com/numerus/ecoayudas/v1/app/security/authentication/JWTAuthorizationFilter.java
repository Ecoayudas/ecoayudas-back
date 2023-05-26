package com.numerus.ecoayudas.v1.app.security.authentication;


import com.numerus.ecoayudas.v1.app.security.utils.JWTUtils;
import com.numerus.ecoayudas.v1.app.security.constants.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(SecurityConstants.HEADER_STRING);
        if (bearerToken != null && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            String token = bearerToken.replace(SecurityConstants.TOKEN_PREFIX, "");
            UsernamePasswordAuthenticationToken usernamePAT = JWTUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);

        }
        filterChain.doFilter(request, response);
    }
}
