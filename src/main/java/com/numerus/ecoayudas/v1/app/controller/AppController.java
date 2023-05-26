package com.numerus.ecoayudas.v1.app.controller;


import com.numerus.ecoayudas.v1.app.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * AppController is a REST controller class that handles API endpoints for user authentication and logout.
 * It is mapped to the specified API version and allows cross-origin requests from "http://localhost:4200".
 */
@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class AppController {

    private final UserDetailsService userDetailsService;

    /**
     * Constructs a new instance of AppController with the specified UserDetailsService.
     *
     * @param userDetailsService The UserDetailsService to be used for user authentication.
     */
    public AppController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    /**
     * Handles the login request by authenticating the user.
     *
     * @param user The UserDto object containing user credentials.
     */
    @PostMapping(value = "/login")
    public void login(@RequestBody UserDto user) {
        userDetailsService.loadUserByUsername(user.getDni());
    }
    /**
     * Handles the logout request by clearing the security context.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @return A ResponseEntity with the message "Logout exitoso".
     * @throws IOException if an I/O error occurs.
     */
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout exitoso");
    }
}
