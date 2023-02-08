package com.example.report.dto;

import com.example.report.entity.Post;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String username;
    private String contents;
    private String title;
    private String password;

}
