package ru.skypro.homework.dto;

import lombok.Data;

/**
 * The AdsDTO class, represents the essence of the DTO declaration
 *
 * @author Marina Gubina
 */
@Data
public class AdsDTO {

    /**
     * Field: User identification number
     */
    private int author;
    /**
     * Field: image name
     */
    private String image;
    /**
     * Field: pk
     */
    private int pk;
    /**
     * Field: price
     */
    private int price;
    /**
     * Field: product description
     */
    private String title;

}
