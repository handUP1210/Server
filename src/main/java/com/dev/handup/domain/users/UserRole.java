package com.dev.handup.domain.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    // 답변을 제외한 모든 기능 USER, 답변을 포함한 모든 기능 ANSWERER
    ROLE_NOT_PERMITTED, ROLE_USER, ROLE_ANSWERER, ROLE_ADMIN
}
