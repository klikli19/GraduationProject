package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.constant.Role;

/**
 * The UserDTO class, represents the user's DTO entity
 *
 * @author Kilikova Anna
 */
@Data
public class UserDTO {
    /**
     * Field: user identification number
     */
    private Integer id;
    /**
     * Field: Email
     */
    private String email;
    /**
     * Field: User name
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
     * Field: User image
     */
    private String image;
    /**
     * Field: role
     */
    private Role role;
}
