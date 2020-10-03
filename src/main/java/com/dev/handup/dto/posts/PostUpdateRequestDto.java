package com.dev.handup.dto.posts;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class PostUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
