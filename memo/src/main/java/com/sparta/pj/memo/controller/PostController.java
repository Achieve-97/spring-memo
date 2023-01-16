package com.sparta.pj.memo.controller;


import com.sparta.pj.memo.dto.CreatePostRequestDto;
import com.sparta.pj.memo.dto.DeletePostRequestDto;
import com.sparta.pj.memo.dto.PostResponseDto;
import com.sparta.pj.memo.dto.UpdatePostRequestDto;
import com.sparta.pj.memo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시물 생성
    @PostMapping("/api/posts")
    public void createPost(@RequestBody CreatePostRequestDto createPostRequest) {
        postService.createPost(createPostRequest);
    }

    // 전체 게시물 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPostList() {
        return postService.getPostList();
    }

    // 게시물 1개 조회
    @GetMapping("/api/posts/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    // 게시물 1개 수정
    @PutMapping("/api/posts/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequestDto updatePostRequest) {
        postService.updatePost(postId, updatePostRequest);
    }

    // 게시물 삭제
    @DeleteMapping("/api/posts/{postId}")
    public void deletePost(@PathVariable Long postId, @RequestBody DeletePostRequestDto deletePostRequest) {
        postService.deletePost(postId, deletePostRequest);
    }
}
