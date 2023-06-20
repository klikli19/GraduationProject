package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Servic CommentServiceImpl
 * Service for the implementation of the comments service
 * @see CommentRepository
 * @see AdRepository
 * @see UserRepository
 * @see CommentMapper
 * @author Bogomolov Ilya
 * @author Kilikova Anna
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    /**
     * Comment Service Implementation Constructor
     * @param commentRepository comment repository
     * @param adRepository ad repository
     * @param userRepository user repository
     * @param commentMapper comment display tool
     */
    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, UserRepository userRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    /**
     * Method of getting all comments
     * @param idAd identifier ads
     * @return Outputs the response wrapper
     */
    @Override
    public ResponseWrapperComment getAllComments(int idAd) {
        List<Comment> comments = commentRepository.findAllByAdId(idAd);
        ResponseWrapperComment responseWrapper = new ResponseWrapperComment();
        responseWrapper.setResults(commentMapper.toCommentsListDto(comments));
        return responseWrapper;
    }

    /**
     * The method outputs the addition of a comment
     * @param id identification number
     * @param comment user's comment
     * @param authentication user identification
     * @return displays the added comment
     */
    @Override
    public CommentDTO addComment(int id, CreateCommentDTO comment, Authentication authentication) {
        Comment newComment = commentMapper.toComment(comment);
        newComment.setAd(adRepository.findById((long) id).get());
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setAuthor(userRepository.getUserByEmail(authentication.getName()));
        commentRepository.save(newComment);

        return commentMapper.toCommentDTO(newComment);
    }

    /**
     * Method of deleting a comment
     * @param adId identifier ads
     * @param commentId comment identification number
     */
    public void deleteComment(int adId, int commentId) {
        commentRepository.deleteByIdAndAdId(adId, commentId);
    }

    /**
     * Update method comment
     * @param adId identifier ads
     * @param commentId comment identification number
     * @param commentDTO user's DTO comment
     * @return outputs the user's DTO comment
     */
    @Override
    public CommentDTO updateComment(int adId, int commentId, CommentDTO commentDTO) {
        Comment updatedComment = commentRepository.findByIdAndAd_Id(commentId, adId);
        updatedComment.setText(commentDTO.getText());
        commentRepository.save(updatedComment);
        return commentMapper.toCommentDTO(updatedComment);
    }
}
