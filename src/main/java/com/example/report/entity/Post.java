package com.example.report.entity;

import com.example.report.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter  //클래스에 Property에 대한 getter 메서드를 자동으로 생성해주는 것
@Entity  //Blog라는 클래스가 JPA Entity클래스로 사용될 것이라는 것, 즉 데이터 베이스에 저장할 데이터 구조를 말한다.
@NoArgsConstructor // 기본생성자를 자동으로 생성할 수 있게 하는 Lombok에서 사용하는 것.
public class Post extends Timestamped {
    @Id  //JPA에서 기본키를 나타내는 필드에 붙인다.
    @GeneratedValue(strategy = GenerationType.AUTO)  //JPA에서 자동으로 값을 생성하는 기능을 나타낸다. 주로 PK를 자동으로 생성하기 위해 사용
    // 보통 AUTO, IDENTITY, SEQUENCE, TABLE 방식들이 있다.
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    public Post(PostRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }

    public void delete(PostRequestDto requestDto, User user) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
        this.user = user;
    }
}