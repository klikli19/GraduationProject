package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.Comment;

/**
 * Interface CommentService
 * The interface is used to work with the database
 * @author Bogomolov Ilya
 * @author Kilikova Anna
 */
public interface CommentService {
    ResponseWrapperComment getAllComments(int id);
    Comment getComment(long commentId);

    CommentDTO addComment(int id, CreateCommentDTO comment, Authentication authentication);

    void deleteComment(int adId, int commentId);
    void deleteAllByAdId(long adId);

    CommentDTO updateComment(int adId, int commentId, CommentDTO comment);


}
