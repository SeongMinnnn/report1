package com.example.report.service;

import com.example.report.dto.PostRequestDto;
import com.example.report.dto.PostResponseDto;
import com.example.report.dto.ResponseDto;
import com.example.report.entity.Post;
import com.example.report.entity.User;
import com.example.report.entity.UserRoleEnum;
import com.example.report.repository.PostRepository;
import com.example.report.jwt.JwtUtil;
import com.example.report.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


// 서비스 계층에서 비즈니스 로직을 구현할 클래스에 붙여서 사용,
// @Service가 묻은 클래스를 빈으로 자동 등록해서 다른 클래스에서 이 클래스의 기능을 사용할 수 있도록 함.
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 요구사항1. 전체 게시글 목록 조회
    // (이때 RSP로 전달해야하는 것이 List가 아닌, DTO로 전달해야함)
    // Transactional 어노테이션이 적용된 메서드에서 수행되는 모든 작업은 하나의 트랜잭션 안에서 수행
    // 즉 한 메서드에서 수행되는 여러 작업이 실패할 경우 이전에 수행된 모든 작업이 롤백되어서 원상태로 돌아간다.
    @Transactional
    public ResponseDto<?> createPost(PostRequestDto requestDto, HttpServletRequest request) {
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

            Post post = postRepository.saveAndFlush(new Post(requestDto, user));
            postRepository.save(post);
            return ResponseDto.success("게시완료");
        } else return ResponseDto.fail(400, "Token Error");
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : postList) {
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return postResponseDtoList;
    }

    @Transactional(readOnly = true)
    public PostRequestDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당글이 없습니다.")
        );
        PostRequestDto postRequestDto = new PostRequestDto();
        return postRequestDto;
    }

    @Transactional
    public ResponseDto<?> update(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        if (jwtUtil.checkToken(request)) {
            Claims claims = jwtUtil.getUserInfoFromToken(jwtUtil.resolveToken(request));

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
            );

            if (user.getRole().equals(UserRoleEnum.ADMIN) || post.getUser().getUsername().equals(user.getUsername())){
                post.update(requestDto, user);
            }else return ResponseDto.fail(400, "수정 권한이 없습니다.");

            return ResponseDto.success("수정 완료");
        } else return ResponseDto.fail(400, "Token Error");
    }
//        String token = jwtUtil.resolveToken(request);
//
//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new IllegalArgumentException("Token Error");
//            }
//            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
//                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
//            );
//            Post post = postRepository.findByIdAndId(id, user.getId()).orElseThrow(
//                    () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
//            );
//            post.update(requestDto);
//
//            return ResponseDto.success("수정 완료");
//        } else return ResponseDto.fail(HttpStatus,"오류가 발생했습니다.");
//    }

    @Transactional
    public ResponseDto<?> deletePost(Long id, HttpServletRequest request) {
        if (jwtUtil.checkToken(request)) {
            Claims claims = jwtUtil.getUserInfoFromToken(jwtUtil.resolveToken(request));

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
            );

            if (user.getRole().equals(UserRoleEnum.ADMIN) || post.getUser().getUsername().equals(user.getUsername())){
                postRepository.delete(post);
            }else return ResponseDto.fail(400,"삭제 권한이 없습니다.");

            return ResponseDto.success("삭제 완료");
        } else return ResponseDto.fail(400, "Token Error");
    }
//        String token = jwtUtil.resolveToken(request);
//        Claims claims;
//
//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new IllegalArgumentException("Token Error");
//            }
//            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
//                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
//            );
//            Post post = postRepository.saveAndFlush(new Post(requestDto));
//            if (requestDto.getUsername().equals(user.getUsername())) {
//                post.delete(requestDto, user);
//            }
//            return ResponseDto.success("삭제 완료");
//        } else return null;
//    }
}