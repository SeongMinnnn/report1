package com.example.report.service;

import com.example.report.dto.PostRequestDto;
import com.example.report.entity.Post;
import com.example.report.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }

    @Transactional
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}
