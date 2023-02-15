package com.example.report.postController;

import com.example.report.dto.PostRequestDto;
import com.example.report.dto.PostResponseDto;
import com.example.report.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController // Json 형태로 응답 반환
@RequiredArgsConstructor // 클래스의 final 필드와 @NonNull 필드를 포함하는 생성자를 자동으로 생성
public class PostController {
    private final PostService postService;
    //게시글 작성
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    @GetMapping("/search")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/search/post")
    public PostRequestDto getPost(@RequestBody Long id) {
        return postService.getPost(id);
    }

    @PutMapping ("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/post/{id}/delete")
    public String deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return "삭제완료";
    }
}