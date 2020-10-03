package com.dev.handup.Controller;

import com.dev.handup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/test")
public class UserApiControllerTest {

    private final UserService userService;

    @GetMapping("/permit-all")
    public Object permitAll() throws Exception {
        return userService.findUsers();
    }

    @GetMapping("/auth")
    public Object permitUser() throws Exception {
        return userService.findUsers();
    }
}
