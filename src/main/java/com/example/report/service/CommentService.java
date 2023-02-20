package com.example.report.service;

import com.example.report.dto.CommentRequestDto;
import com.example.report.dto.CommentResponseDto;
import com.example.report.dto.ResponseDto;
import com.example.report.entity.Comment;
import com.example.report.entity.Post;
import com.example.report.entity.User;
import com.example.report.entity.UserRoleEnum;
import com.example.report.jwt.JwtUtil;
import com.example.report.repository.CommentRepository;
import com.example.report.repository.PostRepository;
import com.example.report.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseDto<?> createComment(Long id, CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
            );

            commentRepository.save(new Comment(requestDto, user, post));
            return ResponseDto.success("댓글 작성 완료");
        } else return ResponseDto.fail(400, "Token Error");
    }

    @Transactional(readOnly = true)
    public List<Comment> getComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당글이 없습니다.")
        );
        List<Comment> comments = new ArrayList<>(post.getComments());
        comments = commentRepository.findAllByOrderByCreatedAtDesc();
//        List<Comment> commentList = commentRepository.findAllByOrderByCommentCreatedAtDesc();
//        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
//        for (Comment comment : commentList) {
//            commentResponseDtoList.add(new CommentResponseDto(comment));
//        }
//        return commentResponseDtoList;
        return comments;
    }

    @Transactional
    public ResponseDto<?> updateComment(Long postId, Long id, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        if (jwtUtil.checkToken(request)) {
            Claims claims = jwtUtil.getUserInfoFromToken(jwtUtil.resolveToken(request));

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
            );

            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
            );

            if (user.getRole().equals(UserRoleEnum.ADMIN) || comment.getUser().getUsername().equals(user.getUsername())) {
                comment.update(commentRequestDto, user);
            } else return ResponseDto.fail(400, "수정 권한이 없습니다.");

            return ResponseDto.success("댓글 수정완료");
        } else return ResponseDto.fail(400, "Token Error");
    }

    @Transactional
    public ResponseDto<?> deleteComment(Long postId, Long id, HttpServletRequest request) {
        if (jwtUtil.checkToken(request)) {
            Claims claims = jwtUtil.getUserInfoFromToken(jwtUtil.resolveToken(request));

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
            );

            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
            );

            if (user.getRole().equals(UserRoleEnum.ADMIN) || comment.getUser().getUsername().equals(user.getUsername())) {
                commentRepository.delete(comment);
            } else return ResponseDto.fail(400, "수정 권한이 없습니다.");

            return ResponseDto.success("삭제 완료");
        } else return ResponseDto.fail(400, "Token Error");
    }
}