package com.example.schedulemanagementdevelop.comment.controller;

import com.example.schedulemanagementdevelop.comment.dto.*;
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

    @PostMapping("/comments") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<CreateCommentResponse> createComment (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @RequestBody CreateCommentRequest request) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(request));
    }

    @GetMapping("/comments") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<List<GetCommentResponse>> getAllComment (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAllComment());
    }

    @GetMapping("/comments/{commentId}") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<GetCommentResponse> getOneComment (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @PathVariable Long commentId) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findOneComment(commentId));
    }

    @PutMapping("/comments/{commentId}") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<UpdateCommentResponse> updateComment (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
                                                                @PathVariable Long commentId, @RequestBody UpdateCommentRequest request) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentId, request));
    }

    @DeleteMapping("/comments/{commentId}") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<Void> deleteComment (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @PathVariable Long commentId) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
