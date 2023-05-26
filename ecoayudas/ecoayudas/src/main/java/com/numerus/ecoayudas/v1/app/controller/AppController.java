package com.numerus.ecoayudas.v1.app.controller;


import com.numerus.ecoayudas.v1.app.dto.UserDto;
import com.numerus.ecoayudas.v1.app.security.JWTUtil;
import com.numerus.ecoayudas.v1.app.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("${api.version}")
public class AppController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;


    public AppController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }
   @PostMapping("/login")
    public String login(@RequestBody UserDto user) {
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getDni());
        String token = JWTUtil.createToken(user.getUsername());

        return token;
    }
 /* @PostMapping("/login")
  public String login(@RequestBody UserDto user) {
      UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUserName());
      if (userDetails == null || !user.getPassword().equals(userDetails.getPassword())) {
          throw new BadCredentialsException("Usuario o contraseña inválidos");
      }else {
          String token = JWTUtil.createToken(user.getUserName());
          return token;
      }*/




  /*@PostMapping("/login")
    public String login(@RequestBody UserDto user) {

       UserDetailsImpl userDetailsImpl=new UserDetailsImpl(user);
        userDetailsServiceImpl.loadUserByUsername(userDetailsImpl.getUsername());
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(user.getUserName());

        String token = JWTUtil.createToken(userDetails);
        log.error(token);
       return token;
       UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUsername());
        String token = JWTUtil.createToken(userDetails.getUsername());

       return token;

    }*/
   /* @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto user) {
        log.error("holi");
       // UserDetails user = userDetailsServiceImpl.loadUserByUsername(userDto.getUserName());
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(user.getUserName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Aquí va la lógica de autenticación con Spring Security
        String token = JWTUtil.createToken(userDetails);

        UserDto response = new UserDto();
        response.setToken(token);
        response.setUserName(userDetails.getUsername());
        response.setPassword(user.getPassword());
        response.setRole(userDetails.getAuthorities().toString());
        log.error("response "+response);
        return ResponseEntity.ok(response);
    }*/
}
