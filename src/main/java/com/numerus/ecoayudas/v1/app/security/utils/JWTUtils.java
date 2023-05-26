package com.numerus.ecoayudas.v1.app.security.utils;


import com.numerus.ecoayudas.v1.app.security.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * This class handles JWT (JSON Web Token) operations.
 */
public class JWTUtils {

    /**
     * Creates a JWT token based on the provided user ID, DNI, and role.
     *
     * @param id   The user ID.
     * @param dni  The user's DNI.
     * @param role The user's role.
     * @return The generated JWT token.
     */
    public static String createToken(Long id, String dni, String role) {
        Date expirationDate = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);
        Claims claims = Jwts.claims().setSubject(dni);
        claims.put("id", id);
        claims.put("authorities", role);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY.getBytes())
                .compact();
    }

    /**
     * Retrieves the authentication information from the provided JWT token.
     *
     * @param token The JWT token.
     * @return An instance of UsernamePasswordAuthenticationToken with the user's information.
     * @throws JwtException If the token is invalid or unable to parse.
     */
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) throws JwtException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET_KEY.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            String dni = claims.getSubject();
            List<GrantedAuthority> authorities = new ArrayList<>();

            String authoritiesString = claims.get("authorities", String.class);
            if (authoritiesString != null && !authoritiesString.isEmpty()) {
                String[] authorityArray = authoritiesString.split(",");
                for (String authority : authorityArray) {
                    authorities.add(new SimpleGrantedAuthority(authority.trim()));
                }
            }
            return new UsernamePasswordAuthenticationToken(dni, null, authorities);
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT token", e);
        }
    }
}

