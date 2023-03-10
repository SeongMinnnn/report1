package com.example.report.Controller;

import com.example.report.dto.PostRequestDto;
import com.example.report.dto.PostResponseDto;
import com.example.report.dto.ResponseDto;
import com.example.report.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api")
@RestController // Json 형태로 응답 반환
@RequiredArgsConstructor // 클래스의 final 필드와 @NonNull 필드를 포함하는 생성자를 자동으로 생성
public class PostController {
    private final PostService postService;
    //게시글 작성
    @PostMapping("/post")   //<?> 와일드카드를 사용 지양, 예외처리를 직접 Custom 해서 사용
    public ResponseDto<?> createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.createPost(requestDto, request);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping ("/post/{id}")
    public ResponseDto<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.update(id, requestDto, request);
    }

    @DeleteMapping("/post/{id}")
    public ResponseDto<?> deletePost(@PathVariable Long id, HttpServletRequest request){
        return postService.deletePost(id, request);
    }
}