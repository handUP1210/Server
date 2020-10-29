package com.dev.handup.dto.users;

import com.dev.handup.domain.BaseTimeEntity;
import com.dev.handup.domain.users.User;
import com.dev.handup.domain.users.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto extends BaseTimeEntity {

    private long id;
    private String email;
    private String password;
    private String name;
    private UserRole role;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public UserDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
