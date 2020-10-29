package com.dev.handup.domain.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    // 답변을 제외한 모든 기능 USER, 답변을 포함한 모든 기능 ANSWERER
    // 첫 계정 생성 시 NOT_PERMITTED, 한번이라도 인증 시 USER
    NOT_PERMITTED("ROLE_NOT_PERMITTED"),
    USER("ROLE_USER"),
    ANSWERER("ROLE_ANSWER"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}
