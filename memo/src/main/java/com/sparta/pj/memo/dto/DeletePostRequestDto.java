package com.sparta.pj.memo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeletePostRequestDto {
    private String password;

    public DeletePostRequestDto(String password) {
        this.password = password;
    }
}
