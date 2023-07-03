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
     * Field: link to image
     */
    private String image;
    /**
     * Field: primary key
     */
    private int pk;
    /**
     * Field: product price
     */
    private int price;
    /**
     * Field: product title
     */
    private String title;

}
