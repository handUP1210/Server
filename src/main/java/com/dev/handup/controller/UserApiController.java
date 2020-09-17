package controller;

import domain.Address;
import domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserApiController {
    private final UserService userService;

    @GetMapping("users/search")
    public Result<List<UserDto>> getAllUser() {
        List<User> findUsers = userService.findUsers();
        List<UserDto> collect = findUsers.stream()
                .map(u -> new UserDto(u.getName(), u.getAddress()))
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
        private String name;
        private Address address;
    }
}
