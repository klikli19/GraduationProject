package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.constant.Role;

/**
 * The SecurityUserDto class, represents the DTO of a user for MyUserDetails
 *
 * @author Gubina Marina
 */
@Data
public class SecurityUserDto {
    /**
     * Field: User's identification number
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
     * Field: role user's
     */
    private Role role;
}

