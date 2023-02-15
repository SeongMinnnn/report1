package com.example.report.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "[a-z0-9]{4,10}",message = "아이디 형식이 일치하지 않습니다.")
    private String username;

    @Column(nullable = false)
    @Pattern(regexp="[a-zA-Z0-9]{8,15}", message = "비밀번호는 영어와 숫자로 포함해서 8~15자리 이내로 입력해주세요.")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}