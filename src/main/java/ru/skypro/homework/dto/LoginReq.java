package ru.skypro.homework.dto;

import lombok.Data;

/**
 * The LoginReq class, represents the essence of the login
 *
 * @author Kilikova Anna
 */
@Data
public class LoginReq {
    /**
     * Field: password
     */
    private String password;
    /**
     * Field: user name
     */
    private String username;

}
