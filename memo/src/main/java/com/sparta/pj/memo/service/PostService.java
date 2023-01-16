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
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));
        return new PostResponseDto(post);
    }

    @Transactional
    public void updatePost(Long postId, UpdatePostRequestDto updatePostRequest) {
        Post postSaved = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));
        if (postSaved.isValidPassword(updatePostRequest.getPassword())) {
            postSaved.update(updatePostRequest.getTitle(), updatePostRequest.getWriter(), updatePostRequest.getContent());
            postRepository.save(postSaved);
        } else {
            throw new IllegalArgumentException("패스워드가 틀렸습니다!");
        }
    }

    @Transactional
    public void deletePost(Long postId, DeletePostRequestDto deletePostRequest) {
        Post postDelete = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));
        String password = deletePostRequest.getPassword();
        if (postDelete.isValidPassword(password)) {
            postRepository.delete(postDelete);
            System.out.println("삭제에 성공했습니다.");
        } else {
            throw new IllegalArgumentException("패스워드가 다릅니다!");
        }
    }

}

