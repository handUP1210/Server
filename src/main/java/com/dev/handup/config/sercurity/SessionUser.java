package com.dev.handup.config.sercurity;

import com.dev.handup.domain.users.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private final String nickname;
    private final String email;

    public SessionUser(User user) {
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}
