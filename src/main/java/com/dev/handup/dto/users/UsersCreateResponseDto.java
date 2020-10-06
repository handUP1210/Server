package com.dev.handup.dto.users;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UsersCreateResponseDto {
    private Long id;

    public UsersCreateResponseDto(Long id) {
        this.id = id;
    }
}
