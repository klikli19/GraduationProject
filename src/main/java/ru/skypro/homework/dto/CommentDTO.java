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
     * Field: pk
     */
    private int pk;
    /**
     * Field: author identification number
     */
    private int author;
    /**
     * Field: author Image
     */
    private String authorImage;
    /**
     * Field: author's name
     */
    private String authorFirstName;
    /**
     * Field: when was the comment created
     */
    private Long createdAt;
    /**
     * Field: text
     */
    private String text;
}
