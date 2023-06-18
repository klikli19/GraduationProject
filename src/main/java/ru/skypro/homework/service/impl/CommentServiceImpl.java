package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.dto.ResponseWrapper;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, UserRepository userRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public ResponseWrapperComment getAllComments(int idAd) {
        List<Comment> comments = commentRepository.findAllByAdId(idAd);
        ResponseWrapperComment responseWrapper = new ResponseWrapperComment();
        responseWrapper.setResults(commentMapper.toCommentsListDto(comments));
        return responseWrapper;
    }

    @Override
    public CommentDTO addComment(int id, CreateCommentDTO comment, Authentication authentication) {
        Comment newComment = commentMapper.toComment(comment);
        newComment.setAd(adRepository.findById((long) id).get());
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setAuthor(userRepository.getUserByEmail(authentication.getName()));
        commentRepository.save(newComment);

        CommentDTO commentDTO = commentMapper.toCommentDTO(newComment);
        return commentDTO;
    }

    public void deleteComment(int adId, int commentId) {
        commentRepository.deleteByIdAndAdId(adId, commentId);
    }

    @Override
    public CommentDTO updateComment(int adId, int commentId, Comment comment) {
        Comment updatedComment = commentRepository.findByIdAndAd_Id(adId, commentId);
        updatedComment.setText(comment.getText());

        CommentDTO commentDTO = CommentMapper.INSTANCE.toCommentDTO(updatedComment);

        return commentDTO;
    }
}
