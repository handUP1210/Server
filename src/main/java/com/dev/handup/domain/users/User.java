package com.dev.handup.domain.users;

import com.dev.handup.domain.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 접근 불가, new user() 불가
@Getter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // PK

    @NotBlank
    private String email;


    private String password;

    private String nickname;

    @Embedded
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING) // 저장값을 문자열로 고정
    private UserRole role = UserRole.NOT_PERMITTED;

    // 빌더
    @Builder
    public User(String email, String password, String nickname, Address address, UserRole role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.role = role;
    }

    public void updateUser(String password, String nickname, Address address) {
        this.password = password;
        this.nickname = nickname;
        this.address = address;
    }

    public User update(String email, String nickname) {
        this.nickname = nickname;
        this.email = email;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}

