package com.example.websitemanageschooltinhdong.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String accessToken;
    private String status;
    private String tokenType = "Bearer";
    private String authorities;


    public LoginResponse(String accessToken, String status,String username,String authorities) {
        this.accessToken = accessToken;
        this.status=status;
        this.username=username;
        this.authorities=authorities;
    }
}