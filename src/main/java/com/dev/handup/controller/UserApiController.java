package com.dev.handup.controller;

import com.dev.handup.domain.Address;
import com.dev.handup.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.dev.handup.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserApiController {
    private final UserService userService;

    @GetMapping("user/{nickname}")
    public UserDto getUser(@PathVariable("nickname") String nickname) {
        User findUser = userService.findUserNickname(nickname);
        return new UserDto(findUser.getEmail(), findUser.getNickname(), findUser.getAddress());
    }

    @PostMapping("user")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest request) {
        User user = User.createUser(request.email, request.password, request.nickname, request.address);
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

    @GetMapping("users")
    public Result<List<UserDto>> getAllUser() {
        List<User> findUsers = userService.findUsers();
        List<UserDto> collect = findUsers.stream()
                .map(u -> new UserDto(u.getEmail(), u.getNickname(), u.getAddress()))
                .collect(Collectors.toList());

        return new Result<>(collect);
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @AllArgsConstructor
    @Data
    static class UserDto {
        private String email;
        private String nickname;
        private Address address;
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
