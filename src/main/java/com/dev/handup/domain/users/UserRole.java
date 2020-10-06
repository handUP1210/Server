package com.dev.handup.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    // 답변을 제외한 모든 기능 USER, 답변을 포함한 모든 기능 ANSWERER
    NOT_PERMITTED("ROLE_NOT_PERMITTED", "권한 없음"),
    USER("ROLE_USER", "일반 사용자"),
    ANSWERER("ROLE_ANSWER", "답변 가능자"),
    ADMIN("ROLE_ADMIN", "슈퍼 유저");

    private final String key;
    private final String title;
}
