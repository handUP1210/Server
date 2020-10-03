package com.dev.handup.dto.posts;

import com.dev.handup.domain.posts.Post;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
    }

}
