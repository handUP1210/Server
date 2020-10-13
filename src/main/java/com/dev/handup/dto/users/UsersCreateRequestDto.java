package com.dev.handup.dto.users;

import com.dev.handup.domain.Address;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UsersCreateRequestDto {
    private String email;
    private String password;
    private String name;
    private Address address;
}
