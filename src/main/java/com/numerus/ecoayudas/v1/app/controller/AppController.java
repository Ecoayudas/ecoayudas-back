package com.numerus.ecoayudas.v1.app.controller;


import com.numerus.ecoayudas.v1.app.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class AppController {
    private final UserDetailsService userDetailsService;


    public AppController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @PostMapping(value = "/login")
    public void login(@RequestBody UserDto user) {
        userDetailsService.loadUserByUsername(user.getDni());
    }
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout exitoso");
    }
}
