package com.dev.handup.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@RequiredArgsConstructor
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id; // PK

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @Embedded
    private Address address;

    // static factory method
    public static User createUser(String email, String password, String nickname, Address address) {
        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setAddress(address);

        return user;
    }
}
