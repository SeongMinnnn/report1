package com.example.report.repository;

import com.example.report.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
//    람다식 사용시 추가 필수 PK값 끼리 연결 내용
    Optional<Post> findByIdAndId(Long id, Long userId);
}
