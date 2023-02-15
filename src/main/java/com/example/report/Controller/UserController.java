package com.example.report.Controller;

import com.example.report.dto.LoginRequestDto;
import com.example.report.dto.SignupRequestDto;
import com.example.report.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.desktop.UserSessionEvent;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/signup")
    public String signupPage(SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return "회원 가입 완료";
    }

    @GetMapping("/login")
    public String loginPage(LoginRequestDto loginRequestDto){
        userService.login(loginRequestDto);
        return "로그인 완료";
    }
}
