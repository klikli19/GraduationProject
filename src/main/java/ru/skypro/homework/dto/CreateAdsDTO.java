package ru.skypro.homework.dto;

import lombok.Data;

/**
 * The CreateAdsDTO class, represents the essence of creating a DTO ad
 *
 * @author Gubina Marina
 */
@Data
public class CreateAdsDTO {
    /**
     * Field: product description
     */
    private String description;
    /**
     * Field: price
     */
    private int price;
    /**
     * Field: title
     */
    private String title;
}
