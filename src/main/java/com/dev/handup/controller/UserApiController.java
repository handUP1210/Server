package com.dev.handup.controller;

import com.dev.handup.domain.Address;
import com.dev.handup.domain.User;
import com.dev.handup.dtos.UserDto;
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

    @GetMapping("users/{nickname}")
    public UserDto getUser(@PathVariable("nickname") String nickname) {
        User findUser = userService.findUserNickname(nickname);
        return UserDto.builder()
                .email(findUser.getEmail())
                .nickname(findUser.getNickname())
                .address(findUser.getAddress())
                .build();
    }

    @PostMapping("users")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest request) {
        User user = User.builder()
                .email(request.email)
                .password(request.password)
                .nickname(request.nickname)
                .address(request.address)
                .build();
        Long id = userService.join(user);
        return new CreateUserResponse(id);
    }

    @PutMapping("users/{id}") // 수정
    public UpdateUserResponse updateUser(@PathVariable("id") Long id,
                                         @RequestBody @Valid UpdateUserRequest request) {
        userService.update(id, request.password, request.address, request.nickname); // 로직
        User findUser = userService.findOne(id); // 쿼리
        return new UpdateUserResponse(findUser.getId(), findUser.getEmail(), findUser.getPassword(), findUser.getNickname(), findUser.getAddress());
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

    @Data
    private static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    private static class CreateUserRequest {
        private String email;
        private String password;
        private String nickname;
        private Address address;
    }

    @Data
    @AllArgsConstructor
    private static class UpdateUserResponse {
        private Long id;
        private String email;
        private String password;
        private String nickname;
        private Address address;
    }

    @Data
    private static class UpdateUserRequest {
        private String email;
        private String password;
        private String nickname;
        private Address address;
    }
}
