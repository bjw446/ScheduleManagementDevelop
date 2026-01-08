package com.example.schedulemanagementdevelop.comment.controller;

import com.example.schedulemanagementdevelop.comment.dto.CreateCommentRequest;
import com.example.schedulemanagementdevelop.comment.dto.CreateCommentResponse;
import com.example.schedulemanagementdevelop.comment.dto.GetCommentResponse;
import com.example.schedulemanagementdevelop.comment.service.CommentService;
import com.example.schedulemanagementdevelop.user.dto.SessionUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CreateCommentResponse> createComment (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @RequestBody CreateCommentRequest request) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(request));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<GetCommentResponse>> getAllComment (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAllComment());
    }
}
