package com.example.report.Controller;

import com.example.report.dto.CommentRequestDto;
import com.example.report.dto.ResponseDto;
import com.example.report.entity.Comment;
import com.example.report.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/post/{postId}")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseDto<?> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.createComment(postId, requestDto, request);
    }

    @GetMapping("/comments")
    public List<Comment> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }

    @PutMapping("/comment/{id}")
    public ResponseDto<?> update(@PathVariable Long postId, @PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.updateComment(postId, id, requestDto, request);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseDto<?> deleteComment(@PathVariable Long postId, @PathVariable Long id, HttpServletRequest request){
        return commentService.deleteComment(postId, id, request);
    }
}
