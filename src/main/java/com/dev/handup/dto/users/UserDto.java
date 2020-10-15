package com.dev.handup.dto.users;

import com.dev.handup.domain.Address;
import com.dev.handup.domain.BaseTimeEntity;
import com.dev.handup.domain.users.User;
import com.dev.handup.domain.users.UserRole;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto extends BaseTimeEntity {

    private long id;
    private String email;
    private String password;
    private String name;
    private Address address;
    private UserRole role;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .address(address)
                .build();
    }

    @Builder
    public UserDto(String name, String email, String password, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
