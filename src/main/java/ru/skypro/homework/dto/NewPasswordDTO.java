package ru.skypro.homework.dto;

import lombok.Data;

/**
 * The NewPasswordDTO class, represents the essence of the new DTO password
 *
 * @author Rogozin Alexandr
 */
@Data
public class NewPasswordDTO {
    /**
     * Field: current Password
     */
    private String currentPassword;
    /**
     * Field: new Password
     */
    private String newPassword;
}
