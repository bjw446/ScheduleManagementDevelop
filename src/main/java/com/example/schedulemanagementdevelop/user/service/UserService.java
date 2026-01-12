package com.example.schedulemanagementdevelop.user.service;

import com.example.schedulemanagementdevelop.config.PasswordEncoder;
import com.example.schedulemanagementdevelop.user.dto.*;
import com.example.schedulemanagementdevelop.user.entity.User;
import com.example.schedulemanagementdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 보안 엔코더 연결하기

    @Transactional // 회원 가입
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) { // 중복 메일 체크
            throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword()); // 암호화된 비밀번호 저장
        User user = new User(
                request.getName(),
                request.getEmail(),
                encodedPassword
        );
        User savedUser = userRepository.save(user);
        return new RegisterUserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public SessionUser login(LoginUserRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("이메일이 일치하지 않습니다.")
        );

        // 입력한 비밀번호를 암호화 값으로 변경하여 저장된 비밀번호와 비교
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new SessionUser(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    @Transactional(readOnly = true)
    public List<GetUserResponse> findAllUser() {
        List<User> users = userRepository.findAll();
        List<GetUserResponse> dtos = new ArrayList<>();

        for (User user : users) {
            GetUserResponse dto = new GetUserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetUserResponse findOneUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저 입니다.")
        );

        return new GetUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저 입니다.")
        );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        user.update(request.getName());
        return new UpdateUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public void deleteUser(Long userId) {
        boolean existence = userRepository.existsById(userId);

        if (!existence) {
            throw new IllegalArgumentException("없는 유저 입니다.");
        }
        userRepository.deleteById(userId);
    }
}
