package com.dev.handup.controller;

import com.dev.handup.domain.users.User;
import com.dev.handup.dto.users.*;
import com.dev.handup.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserApiController {
    private final UserService userService;

    @PostMapping("users")
    public UsersCreateResponseDto signUpUser(@RequestBody @Valid UsersCreateRequestDto request) {
        UserDto userDto = UserDto.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .address(request.getAddress())
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

    @GetMapping("users/{nickname}")
    public UserDto getUser(@PathVariable("nickname") String nickname) {
        User findUser = userService.findUserNickname(nickname);
        return UserDto.builder()
                .email(findUser.getEmail())
                .nickname(findUser.getNickname())
                .address(findUser.getAddress())
                .build();
    }

    @PutMapping("users/{id}") // 수정
    public UsersUpdateResponseDto updateUser(@PathVariable("id") Long id,
                                             @RequestBody @Valid UsersUpdateRequestDto request) {
        userService.update(id, request.getPassword(), request.getAddress(), request.getNickname()); // 로직
        User findUser = userService.findOne(id); // 쿼리
        return new UsersUpdateResponseDto(findUser.getId(), findUser.getEmail(), findUser.getPassword(), findUser.getNickname(), findUser.getAddress());
    }

    @GetMapping("users/list")
    public Result<List<UserDto>> getAllUser() {
        List<User> findUsers = userService.findUsers();
        List<UserDto> collect = findUsers.stream()
                .map(u -> UserDto.builder()
                        .email(u.getEmail())
                        .nickname(u.getNickname())
                        .address(u.getAddress())
                        .build())
                .collect(Collectors.toList());

        return new Result<>(collect);
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
