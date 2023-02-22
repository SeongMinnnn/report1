package com.example.report.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String adminToken = "";
    private boolean admin = false;
}
