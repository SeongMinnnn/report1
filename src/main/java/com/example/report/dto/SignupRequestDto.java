package com.example.report.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String adminToken = "";
    private boolean admin = false;
}
