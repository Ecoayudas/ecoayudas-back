package com.numerus.ecoayudas.v1.app.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String dni;
    private String password;
    private String role;
    private String token;

    public UserDto(String username,String password, String role,String token) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.token=token;
    }

    public UserDto() {
    }
}
