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
     * Field: primary key
     */
    private int pk;
    /**
     * Field: username
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
     * Field: link to image
     */
    private String image;
    /**
     * Field: phone number user's
     */
    private String phone;
    /**
     * Field: product price
     */
    private int price;
    /**
     * Field: product title
     */
    private String title;
}
