package com.sparta.pj.memo.service;

import com.sparta.pj.memo.dto.CreatePostRequestDto;
import com.sparta.pj.memo.dto.DeletePostRequestDto;
import com.sparta.pj.memo.dto.PostResponseDto;
import com.sparta.pj.memo.dto.UpdatePostRequestDto;
import com.sparta.pj.memo.entity.Post;
import com.sparta.pj.memo.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Transactional
    public List<PostResponseDto> getPostList() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseList = new ArrayList<>();
        for (Post post : postList) {
            postResponseList.add(new PostResponseDto((post)));
        }
        return postResponseList;
    }

    @Transactional
    public void createPost(CreatePostRequestDto createPostRequest) {
        Post post = new Post(createPostRequest.getTitle(), createPostRequest.getWriter(), createPostRequest.getPassword(), createPostRequest.getContent());
        postRepository.save(post);
    }

    @Transactional
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        return new PostResponseDto(post);
    }

    @Transactional
    public void update(Long postId, UpdatePostRequestDto updatePostRequest) {
        Post postSaved = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        if (postSaved.isValidPassword(updatePostRequest.getPassword())) {
            postSaved.update(updatePostRequest.getTitle(), updatePostRequest.getWriter(), updatePostRequest.getContent());
            postRepository.save(postSaved);
        } else {
            throw new IllegalArgumentException("패스워드 불일치");
        }
    }

    @Transactional
    public void delete(Long postId, DeletePostRequestDto deletePostRequest) {
        Post postDelete = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        String password = deletePostRequest.getPassword();
        if (postDelete.isValidPassword(password)) {
            postRepository.delete(postDelete);
            System.out.println("삭제 성공.");
        } else {
            throw new IllegalArgumentException("패스워드 불일치");
        }
    }

}

