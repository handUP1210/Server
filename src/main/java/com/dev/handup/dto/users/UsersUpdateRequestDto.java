package com.dev.handup.dto.users;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UsersUpdateRequestDto {

    private String email;
    private String password;
    private String name;
}
