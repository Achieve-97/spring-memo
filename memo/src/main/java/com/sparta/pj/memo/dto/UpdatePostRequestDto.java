package com.sparta.pj.memo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class UpdatePostRequestDto {
    private String title;
    private String writer;
    private String content;
    private String password;

    public UpdatePostRequestDto(String title, String writer, String content, String password) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.password = password;
    }
}
