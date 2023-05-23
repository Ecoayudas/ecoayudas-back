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


public class JWTUtils {



    public static String createToken(Long id, String dni, String role) {
        Date expirationDate = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);
        Claims claims = Jwts.claims().setSubject(dni);
        claims.put("id",id);
        claims.put("authorities", role);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY.getBytes())
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) throws JwtException{
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
            return new UsernamePasswordAuthenticationToken(dni, null,authorities);

        } catch (JwtException e) {
             throw new JwtException("Token JWT inválido ", e);
        }
    }
}
