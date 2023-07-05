package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

/**
 * The ResponseWrapperComment class, represents the essence of comments and responses
 *
 * @author Bogomolov Ilya
 */
@Data
public class ResponseWrapperComment {
    /**
     * Field: count
     */
    private int count;
    /**
     * Field: list results
     */
    private List<CommentDTO> results;
}
