package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.constant.Role;

/**
 * The RegisterReq class, represents the essence of registration
 */
@Data
public class RegisterReq {
    /**
     * Field: user name
     */
    private String username;
    /**
     * Field: password
     */
    private String password;
    /**
     * Field: first name
     */
    private String firstName;
    /**
     * Field: last name
     */
    private String lastName;
    /**
     * Field: phone
     */
    private String phone;
    /**
     * Field: role
     */
    private Role role;
}
