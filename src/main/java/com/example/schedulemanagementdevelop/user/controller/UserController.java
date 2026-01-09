package com.example.schedulemanagementdevelop.user.controller;

import com.example.schedulemanagementdevelop.user.dto.*;
import com.example.schedulemanagementdevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser (@Valid @RequestBody RegisterUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login (@Valid @RequestBody LoginUserRequest request, HttpSession session) {
        SessionUser sessionUser = userService.login(request);
        session.setAttribute("loginUser", sessionUser);

        LoginUserResponse response = new LoginUserResponse(sessionUser.getId(), sessionUser.getName(), sessionUser.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout (@SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, HttpSession session) {
        if (sessionUser == null) {
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getAllUser (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUser());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getOneUser (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @PathVariable Long userId) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOneUser(userId));
    }

    @PutMapping("/users")
    public ResponseEntity<UpdateUserResponse> updateUser (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @RequestBody UpdateUserRequest request) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(sessionUser.getId(), request));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @PathVariable Long userId) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
