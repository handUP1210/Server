package com.dev.handup.dto.users;

import com.dev.handup.domain.Address;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UsersUpdateResponseDto {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Address address;

    public UsersUpdateResponseDto(Long id, String email, String password, String nickname, Address address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
    }
}
