package com.example.report.dto;

import com.example.report.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private String username;
    private String contents;
    private String title;
    private String password;

    public PostResponseDto(Post post) {
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.title = post.getTitle();
        this.password = post.getPassword();
    }

    // 선언시에만 형태를 지정해서 넣어준다 ()
    public PostResponseDto() {
    }
}