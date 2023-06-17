package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
    }

    @Override
    public ResponseWrapperComment getAllComments(int idAd) {
        List<Comment> comments = (List<Comment>) commentRepository.findByAd_Id(idAd);
        List<CommentDTO> commentDTOList = CommentMapper.INSTANCE.commentsToCommentsListDto(comments);

        ResponseWrapperComment wrapperComment = new ResponseWrapperComment();
        wrapperComment.setResults(commentDTOList);
        wrapperComment.setCount(comments.size());

        return wrapperComment;
    }

    @Override
    public CreateCommentDTO addComment(int id, String comment) {
        CreateCommentDTO commentDTO = new CreateCommentDTO();
        commentDTO.setText(comment);

        Comment newComment = CommentMapper.INSTANCE.createCommentDtoToComment(commentDTO);
        newComment.setAd(adRepository.findById((long) id).get());
        return commentDTO;
    }

    public void deleteComment(int adId, int commentId) {
        commentRepository.deleteByPkAndAd_Id(adId, commentId);
    }

    @Override
    public CommentDTO updateComment(int adId, int commentId, Comment comment) {
        Comment updatedComment = commentRepository.findByPkAndAd_Id(adId, commentId);
        updatedComment.setText(comment.getText());

        CommentDTO commentDTO = CommentMapper.INSTANCE.commentToCommentDTO(updatedComment);

        return commentDTO;
    }
}
