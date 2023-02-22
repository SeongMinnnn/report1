package com.example.report.entity;

import com.example.report.dto.CommentResponseDto;
import com.example.report.dto.PostRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter  //클래스에 Property에 대한 getter 메서드를 자동으로 생성해주는 것
@Entity  //Blog라는 클래스가 JPA Entity클래스로 사용될 것이라는 것, 즉 데이터 베이스에 저장할 데이터 구조를 말한다.
@NoArgsConstructor // 기본생성자를 자동으로 생성할 수 있게 하는 Lombok에서 사용하는 것.
public class Post extends Timestamped {
    @Id  //JPA에서 기본키를 나타내는 필드에 붙인다.
    @GeneratedValue(strategy = GenerationType.AUTO)  //JPA에서 자동으로 값을 생성하는 기능을 나타낸다. 주로 PK를 자동으로 생성하기 위해 사용
    // 보통 AUTO, IDENTITY, SEQUENCE, TABLE 방식들이 있다.
    private Long id;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;
//  토큰 사용으로 필요 X
//    @Column(nullable = false)
//    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(PostRequestDto requestDto, User user){
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.user = user;
    }

    public void update(PostRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }
//    public void addComment(Comment comment){
//        this.comments.add(comment);
//        if(comment.getPost() != this){
//            comment.setPost(this);
//        }
//    }
//    public void removeComment(Comment comment){
//        comments.remove(comment);
////        comment.setPost(null);
//    }
}