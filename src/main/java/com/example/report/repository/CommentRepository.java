package com.example.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.report.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByCreatedAtDesc();
}
