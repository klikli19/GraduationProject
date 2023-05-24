package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.service.ResponseWrapperCommentService;
import ru.skypro.homework.service.impl.CommentServiceImpl;
import ru.skypro.homework.service.impl.CreateCommentImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class CommentController {
    private final CommentServiceImpl commentService;
    private final CreateCommentImpl createComment;
    private final ResponseWrapperCommentService responseWrapperComment;

//    public CommentController(CommentServiceImpl commentService,
//                             CreateCommentImpl createComment,
//                             ResponseWrapperCommentService responseWrapperComment) {
//        this.commentService = commentService;
//        this.createComment = createComment;
//        this.responseWrapperComment = responseWrapperComment;
//    }

    @GetMapping("{id}/comments")
    public ResponseEntity<?> comment (@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<?> addComment (@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> comment (@PathVariable int adId,
                                            @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> addComment (@PathVariable int adId,
                                         @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }
}
