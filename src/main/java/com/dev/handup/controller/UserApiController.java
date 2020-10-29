package com.dev.handup.controller;

import com.dev.handup.config.sercurity.token.JwtTokenProvider;
import com.dev.handup.domain.users.User;
import com.dev.handup.domain.users.UserRepository;
import com.dev.handup.dto.users.*;
import com.dev.handup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserApiController {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody @Valid UsersCreateRequestDto requestDto) {
        UserDto userDto = UserDto.builder()
                .email(requestDto.getEmail())
                .name(requestDto.getName())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();

        return userRepository.save(userDto.toEntity()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody @Valid UsersCreateRequestDto requestDto) {
        User findUser = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), findUser.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(findUser.getEmail(), findUser.getRole());
    }

    @PostMapping("users")
    public UsersCreateResponseDto signUpUser(@RequestBody @Valid UsersCreateRequestDto request) {
        UserDto userDto = UserDto.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .build();
        Long id = userService.joinUser(userDto);
        return new UsersCreateResponseDto(id);
    }

    @GetMapping("users")
    public void loginUser(@RequestBody @Valid UsersCreateRequestDto request) throws Exception {
        UserDto userDto = UserDto.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        userService.loginUser(userDto);
    }

    @GetMapping("users/{name}")
    public UserDto getUser(@PathVariable("name") String name) {
        User findUser = userService.findUserName(name);
        return UserDto.builder()
                .email(findUser.getEmail())
                .name(findUser.getName())
                .build();
    }

    @PutMapping("users/{id}") // 수정
    public UsersUpdateResponseDto updateUser(@PathVariable("id") Long id,
                                             @RequestBody @Valid UsersUpdateRequestDto request) {
        userService.update(id, request.getPassword(), request.getName()); // 로직
        User findUser = userService.findOne(id); // 쿼리
        return new UsersUpdateResponseDto(findUser.getId(), findUser.getEmail(), findUser.getPassword(), findUser.getName());
    }

    @GetMapping("users/list")
    public UsersListResultData<List<UserDto>> getAllUser() {
        List<User> findUsers = userService.findUsers();
        List<UserDto> collect = findUsers.stream()
                .map(u -> UserDto.builder()
                        .email(u.getEmail())
                        .name(u.getName())
                        .build())
                .collect(Collectors.toList());

        return new UsersListResultData<>(collect);
    }
}
