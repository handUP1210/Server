package com.dev.handup.dto.users;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UsersUpdateResponseDto {
    private Long id;
    private String email;
    private String password;
    private String name;

    public UsersUpdateResponseDto(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
