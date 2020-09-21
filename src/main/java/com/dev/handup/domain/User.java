package com.dev.handup.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 접근 불가, new user() 불가
@Getter
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

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_NOT_PERMITTED;

    // 빌더
    @Builder
    public User(String email, String password, String nickname, Address address) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
    }

    public void updateUser(String password, String nickname, Address address) {
        this.password = password;
        this.nickname = nickname;
        this.address = address;
    }
}
