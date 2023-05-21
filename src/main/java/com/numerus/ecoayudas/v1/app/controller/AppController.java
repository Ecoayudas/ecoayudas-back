package com.numerus.ecoayudas.v1.app.controller;


import com.numerus.ecoayudas.v1.app.dto.UserDto;
import com.numerus.ecoayudas.v1.app.security.JWTUtil;
import com.numerus.ecoayudas.v1.app.security.UserDetailsImpl;
import com.numerus.ecoayudas.v1.app.security.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class AppController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserDetailsService userDetailsService;


    public AppController(UserDetailsServiceImpl userDetailsServiceImpl, UserDetailsService userDetailsService) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.userDetailsService = userDetailsService;
    }
    @PostMapping(value = "/login")
    public String login(@RequestBody UserDto user) {
        UserDetailsImpl userDetails = (UserDetailsImpl)  userDetailsService.loadUserByUsername(user.getDni());
        String token = JWTUtil.createToken(userDetails.getId(), userDetails.getDni(), userDetails.getAuthorities().iterator().next().toString());

        return token;
    }
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout exitoso");
    }
}
