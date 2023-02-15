package com.example.report.dto;

import com.example.report.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class PostResponseDto {
    private String username;
    private String contents;
    private String title;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.title = post.getTitle();
        this.password = post.getPassword();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}