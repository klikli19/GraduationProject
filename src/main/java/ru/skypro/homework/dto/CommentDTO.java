package ru.skypro.homework.dto;

import lombok.Data;

/**
 * The CommentDTO class, represents the essence of the product comment
 *
 * @author Bogomolov Ilya
 */
@Data
public class CommentDTO {
    /**
     * Field: primary key
     */
    private int pk;
    /**
     * Field: author identification number
     */
    private int author;
    /**
     * Field: link to the author's avatar
     */
    private String authorImage;
    /**
     * Field: author's name
     */
    private String authorFirstName;
    /**
     * Field: comment creation time
     */
    private Long createdAt;
    /**
     * Field: text
     */
    private String text;
}
