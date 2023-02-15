package com.example.report.dto;

import com.example.report.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
    private String username;
    private String contents;
    private String title;
    private String password;
    private LocalDateTime createdAt;

    public PostRequestDto(Post post) {
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.title = post.getTitle();
        this.password = post.getPassword();
        this.createdAt = post.getCreatedAt();
    }
}
