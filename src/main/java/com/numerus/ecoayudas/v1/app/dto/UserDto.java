package com.numerus.ecoayudas.v1.app.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserDto {
    private long id;
    private String username;
    private String dni;
    private String password;
    private String role;
    private String token;




    public UserDto(Long id,String username,String password, String role,String token) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.token=token;
    }

    public UserDto() {
    }
}
