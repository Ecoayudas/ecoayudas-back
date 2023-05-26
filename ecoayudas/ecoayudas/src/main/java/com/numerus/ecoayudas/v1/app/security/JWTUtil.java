package com.numerus.ecoayudas.v1.app.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Slf4j
public class JWTUtil {
    //@Value("${security.jwt.secret}")
    private  static String secretKey="hKj$#dLm7@*fT9z";
   // @Value("${security.jwt.ttlMillis}")
    private  static int ttlMillis=86400000;

    public JWTUtil(String secretKey, int ttlMillis) {
        this.secretKey = secretKey;
        this.ttlMillis = ttlMillis;
    }


    public static String createToken(String userName) {


       // List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        Map<String, Object> extra = new HashMap<>();
        extra.put("userName", userName);


      // extra.put("authorities", userDetails.getAuthorities());

        String token = Jwts
                .builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
                //.claim("authorities",userDetails.getAuthorities())
                .addClaims(extra)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes())
                .compact();

        return token;
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            String userName = claims.getSubject();
            List<GrantedAuthority> authorities=new ArrayList<>();

            return new UsernamePasswordAuthenticationToken(userName, null,Collections.emptyList());
        } catch (JwtException e) {

            return null;
        }
    }
}
