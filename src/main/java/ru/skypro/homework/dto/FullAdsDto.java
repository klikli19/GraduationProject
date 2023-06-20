package ru.skypro.homework.dto;

import lombok.Data;

/**
 * The FullAdsDto class, represents the essence of the full product announcement
 *
 * @author Gubina Marina
 */
@Data
public class FullAdsDto {
    /**
     * Field: pk
     */
    private int pk;
    /**
     * Field: user name
     */
    private String authorFirstName;
    /**
     * Field: user's last name
     */
    private String authorLastName;
    /**
     * Field: product description
     */
    private String description;
    /**
     * Field: Email
     */
    private String email;
    /**
     * Field: product image
     */
    private String image;
    /**
     * Field: phone number
     */
    private String phone;
    /**
     * Field: product price
     */
    private int price;
    /**
     * Field: title
     */
    private String title;
}
