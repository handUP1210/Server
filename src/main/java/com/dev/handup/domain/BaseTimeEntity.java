package com.dev.handup.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 상속 시 필드들을 column으로 인식
public abstract class BaseTimeEntity {

    @CreatedDate// 생성 시
    private LocalDateTime createdDate;

    @LastModifiedDate // 수정 시 자동 시간 저장
    private LocalDateTime modifiedDate;
}
