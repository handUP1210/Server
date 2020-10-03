package com.dev.handup.controller;

import com.dev.handup.domain.Address;
import com.dev.handup.domain.users.User;
import com.dev.handup.dto.users.UserDto;
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
    public CreateUserResponse signUpUser(@RequestBody @Valid CreateUserRequest request) {
        UserDto userDto = UserDto.builder()
                .email(request.email)
                .password(request.password)
                .nickname(request.nickname)
                .address(request.address)
                .build();
        Long id = userService.joinUser(userDto);
        return new CreateUserResponse(id);
    }

    @GetMapping("users")
    public void loginUser(@RequestBody @Valid CreateUserRequest request) throws Exception {
        UserDto userDto = UserDto.builder()
                .email(request.email)
                .password(request.password)
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
