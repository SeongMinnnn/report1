package com.example.report.postController;

import com.example.report.dto.PostRequestDto;
import com.example.report.entity.Post;
import com.example.report.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // RESTAPI 컨트롤러 클래스에 사용, 해당 어노테이션이 적용된 메서드를 자동으로 실행한 결과를 HTTP 응답으로 반환
@RequiredArgsConstructor // 클래스의 final 필드와 @NonNull 필드를 포함하는 생성자를 자동으로 생성
public class PostController {
    private final PostService postService;
    //게시글 작성
    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    @GetMapping("/api/search")
    public List<PostRequestDto> getPosts(@PathVariable Long id) {
        return postService.getPosts();
    }

    @GetMapping("/api/search/post")
    public PostRequestDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping ("/api/post/{id}")
    public PostRequestDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

}