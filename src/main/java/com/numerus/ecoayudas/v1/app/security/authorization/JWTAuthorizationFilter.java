package com.numerus.ecoayudas.v1.app.security.authorization;


import com.numerus.ecoayudas.v1.app.security.utils.JWTUtils;
import com.numerus.ecoayudas.v1.app.security.constants.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWTAuthorizationFilter is responsible for intercepting requests and validating JWT tokens.
 */
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    /**
     * Intercepts the request and performs JWT token validation.
     *
     * @param request     HttpServletRequest object
     * @param response    HttpServletResponse object
     * @param filterChain FilterChain object
     * @throws ServletException if an error occurs during the filter processing
     * @throws IOException      if an I/O error occurs during the filter processing
     */
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
