package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.constant.Role;

/**
 * The Dto Security User class, represents the Dto Security User
 *
 * @author Gubina Marina
 */
@Data
public class SecurityUserDto {
    /**
     * Field: Ab identification number
     */
    private Integer id;
    /**
     * Field: Email
     */
    private String email;
    /**
     * Field: password
     */
    private String password;
    /**
     * Field: role
     */
    private Role role;
}

