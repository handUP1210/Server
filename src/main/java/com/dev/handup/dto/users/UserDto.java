package com.dev.handup.dto.users;

import com.dev.handup.domain.Address;
import com.dev.handup.domain.users.User;
import com.dev.handup.domain.users.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class UserDto {
    private long id;
    private String email;
    private String password;
    private String nickname;
    private Address address;
    private UserRole role;
    private LocalDateTime localDateTime;

    public User toEntity() {
        return User.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .address(address)
                .build();
    }

    @Builder
    public UserDto(String nickname, String email, String password, Address address) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
