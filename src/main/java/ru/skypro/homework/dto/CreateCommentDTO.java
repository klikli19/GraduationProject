package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.entity.Comment;

@Data
public class CreateCommentDTO {
    private String text;

}
