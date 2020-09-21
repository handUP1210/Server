package com.dev.handup.dtos;

import com.dev.handup.domain.Address;
import com.dev.handup.domain.User;
import com.dev.handup.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDto {
    private long id;
    private String email;
    private String password;
    private String nickname;
    private Address address;
    private UserRole role;

    public Object toEntity() {
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
