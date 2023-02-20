package com.example.report.entity;

import com.example.report.dto.CommentRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    public Comment(CommentRequestDto requestDto, User user, Post post) {
        this.comment = requestDto.getComment();
        this.user = user;
        this.post = post;
    }

    public void update(CommentRequestDto requestDto, User user) {
        this.comment = requestDto.getComment();
        this.user = user;
    }
//    public void setPost(Post post) {
//        this.post = post;
//        if (!post.getComments().contains(this)){
//            post.getComments().add(this);
//        }
//    }
}
