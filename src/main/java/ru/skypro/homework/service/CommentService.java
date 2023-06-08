package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.Comment;

public interface CommentService {
    ResponseWrapperComment getAllComments(int id);

    CreateCommentDTO addComment(int id, String comment);

    void deleteComment(int adId, int commentId);

    CommentDTO updateComment(int adId, int commentId, Comment comment);
}
