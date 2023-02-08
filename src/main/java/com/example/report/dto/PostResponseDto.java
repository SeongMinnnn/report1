package com.example.report.dto;

import com.example.report.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private String msg;
    private int statusCode;
    private String username;
    private String contents;
    private String title;
    private String password;

    public PostResponseDto(String msg, int statusCode, String username, String contents, String title, String password) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.username = username;
        this.contents = contents;
        this.title = title;
        this.password = password;
    }

    // 선언시에만 형태를 지정해서 넣어준다 ()
    public PostResponseDto(Post post) {
    }
    
    public PostResponseDto(String msg, int statusCode){
        this.msg = msg;
        this.statusCode = statusCode;
    }
}