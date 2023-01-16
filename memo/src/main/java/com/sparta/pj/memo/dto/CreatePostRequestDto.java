package com.sparta.pj.memo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostRequestDto {
    private String title;
    private String writer;
    private String password;
    private String content;


    public CreatePostRequestDto(String title, String writer, String password, String content) {
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
    }
}

