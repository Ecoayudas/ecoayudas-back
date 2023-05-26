package com.numerus.ecoayudas.v1.app.security;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class JWTUtil {

    //@Value("${security.jwt.secret}")
    private  static final String secretKey="secr3t0";
   // @Value("${security.jwt.ttlMillis}")
    private  static final int ttlMillis=86400000;

   /* public JWTUtil(String secretKey, int ttlMillis) {
        this.secretKey = secretKey;
        this.ttlMillis = ttlMillis;
    }*/

    public static String createToken(String dni, String role) {
        Date expirationDate = new Date(System.currentTimeMillis() + ttlMillis);
        Claims claims = Jwts.claims().setSubject(dni);
        claims.put("authorities", role);
       /* List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        claims.put("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));*/

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }


   /* public static String createToken(String dni,String role) {


       // List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        Map<String, Object> extra = new HashMap<>();
        //extra.put("userName", userName);

        extra.put("dni",dni);
       extra.put("authorities", role);

        String token = Jwts
                .builder()
                .setSubject(dni)
                .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
                .claim("authorities",role)
                .addClaims(extra)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes())
                .compact();

        return token;
    }*/

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            String dni = claims.getSubject();
            //List<GrantedAuthority> authorities=new ArrayList<>();
          /*  List<String> authoritiesString = (List<String>) claims.get("authorities");
            List<GrantedAuthority> authorities = authoritiesString.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());*/
            List<GrantedAuthority> authorities = new ArrayList<>();

            String authoritiesString = claims.get("authorities", String.class);
            if (authoritiesString != null && !authoritiesString.isEmpty()) {
                String[] authorityArray = authoritiesString.split(",");
                for (String authority : authorityArray) {
                    authorities.add(new SimpleGrantedAuthority(authority.trim()));
                }
            }
            log.error("getauthentication " + dni);
            log.error("getauthentication " +String.valueOf(authorities));
            return new UsernamePasswordAuthenticationToken(dni, null,authorities);

        } catch (JwtException e) {

            log.error("Error al desencriptar el token JWT: " + e.getMessage());
            return null;
            //throw new JwtAuthenticationException("Token JWT inválido", e);
        }
    }
}
