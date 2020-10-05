package com.dev.handup.service;

import com.dev.handup.domain.posts.Post;
import com.dev.handup.domain.posts.PostRepository;
import com.dev.handup.dto.posts.PostsResponseDto;
import com.dev.handup.dto.posts.PostsSaveRequestDto;
import com.dev.handup.dto.posts.PostsUpdateRequestDto;
import com.dev.handup.dto.posts.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId(); // 엔티티 반환 후 아이디 반환
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {

        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다."));
        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc(){
        return postRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다"));
        postRepository.delete(post);
    }

    public PostsResponseDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다"));

        return new PostsResponseDto(post);
    }


}
