package com.dev.handup.service;

import com.dev.handup.domain.posts.Post;
import com.dev.handup.domain.posts.PostRepository;
import com.dev.handup.dto.posts.PostResponseDto;
import com.dev.handup.dto.posts.PostSaveRequestDto;
import com.dev.handup.dto.posts.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId(); // 엔티티 반환 후 아이디 반환
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {

        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다."));
        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostResponseDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다"));

        return new PostResponseDto(post);
    }
}
