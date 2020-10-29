package com.dev.handup.domain.users;

import com.dev.handup.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 접근 불가, new user() 불가
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Column(name = "user_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 10)
    private String name;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING) // 저장값을 문자열로 고정
    private UserRole role = UserRole.USER;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<UserRole> roles = new ArrayList<>();

    @Builder
    public User(String email, String password, String name ) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    // 로그인 로직을 통해 ROLE_USER로 권한 변경

    @Builder
    public User(String email, String password, String name, UserRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public void updateUser(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public User update(String email, String name) {
        this.name = name;
        this.email = email;

        return this;
    }

    public String getRoleKey() {
        return this.getRole().getValue();
    }
}

