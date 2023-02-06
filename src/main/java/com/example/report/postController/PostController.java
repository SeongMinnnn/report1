package com.example.report.postController;

import com.example.report.dto.PostRequestDto;
import com.example.report.entity.Post;
import com.example.report.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    //게시글 작성
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }


    @GetMapping("/api/search")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

}