package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.skypro.homework.service.ResponseWrapperCommentService;

@Service
public class ResponseWrapperCommentImpl implements ResponseWrapperCommentService {
    public ResponseEntity<?> comment (@PathVariable int id) {
        return ResponseEntity.ok().build();
    }
}
