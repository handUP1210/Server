package com.dev.handup.controller;

import com.dev.handup.dto.posts.PostsListResponseDto;
import com.dev.handup.dto.posts.PostsResponseDto;
import com.dev.handup.dto.posts.PostsSaveRequestDto;
import com.dev.handup.dto.posts.PostsUpdateRequestDto;
import com.dev.handup.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class PostApiController {

    private final PostService postService;

    @GetMapping("/")
    public List<PostsListResponseDto> findByAllAsc() {
        return postService.findAllASC();
    }

    @PostMapping("posts")
    public Long savePost(@RequestBody PostsSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @GetMapping("posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}
