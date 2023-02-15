package com.example.report.Controller;

import com.example.report.dto.LoginRequestDto;
import com.example.report.dto.SignupRequestDto;
import com.example.report.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/signup")
    public String signupPage(@RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return "회원 가입 완료";
    }

    @PostMapping("/login")
    public String loginPage(@RequestBody LoginRequestDto loginRequestDto){
        userService.login(loginRequestDto);
        return "로그인 완료";
    }
}
