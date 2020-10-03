package com.dev.handup.controller;

import com.dev.handup.dto.posts.PostResponseDto;
import com.dev.handup.dto.posts.PostSaveRequestDto;
import com.dev.handup.dto.posts.PostUpdateRequestDto;
import com.dev.handup.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class PostApiController {

    private final PostService postService;

    @PostMapping("posts")
    public Long savePost(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @GetMapping("post/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }
}
