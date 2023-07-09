package ru.skypro.homework.dto;

import lombok.Data;

/**
 * The LoginReq class, represents the essence of the login
 */
@Data
public class LoginReq {
    /**
     * Field: password
     */
    private String password;
    /**
     * Field: username
     */
    private String username;

}
