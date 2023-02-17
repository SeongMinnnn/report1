package com.example.report.dto;

import com.example.report.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String username;
    private String contents;
    private String title;
    private LocalDateTime createdAt;

    public PostRequestDto(Post post) {
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
    }
}
