package com.example.report.dto;

import com.example.report.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
    private String contents;
    private String title;
}
