package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.dto.ResponseWrapper;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import java.util.Collection;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class CommentController {
    private final CommentServiceImpl commentService;

    @GetMapping("{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getAllComments (@PathVariable int id) {
        return ResponseEntity.ok( commentService.getAllComments(id));
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<CommentDTO> addComment (@PathVariable int id,
                                                  @RequestBody CreateCommentDTO comment,
                                                  Authentication authentication) {
        return ResponseEntity.ok(commentService.addComment(id, comment, authentication));
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment (@PathVariable int adId,
                                               @PathVariable int commentId) {
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment (@PathVariable int adId,
                                         @PathVariable int commentId,
                                                     @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, comment));
    }
}
