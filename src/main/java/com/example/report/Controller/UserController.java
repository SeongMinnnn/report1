package com.example.report.Controller;

import com.example.report.dto.SignupRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/signup")
    public String signupPage(@RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return "회원 가입 완료";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "로그인 완료";
    }
}
