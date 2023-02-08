package com.example.report.dto;

import com.example.report.entity.Post;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String username;
    private String contents;
    private String title;
    private String password;

    public PostRequestDto(String username, String contents, String title, String password) {
        this.username = username;
        this.contents = contents;
        this.title = title;
        this.password = password;
    }

    public PostRequestDto(Post post) {
    }
}
