package com.numerus.ecoayudas.v1.app.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) class for User entities.
 */
@Data
public class UserDto {
    private long id;
    private String username;
    private String dni;
    private String password;
    private String role;
    private String token;


    /**
     * Constructor for UserDto.
     *
     * @param id       The ID of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param role     The role of the user.
     * @param token    The token associated with the user.
     */

    public UserDto(Long id, String username, String password, String role, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.token = token;
    }

    /**
     * Default constructor for UserDto.
     */
    public UserDto() {
    }
}
