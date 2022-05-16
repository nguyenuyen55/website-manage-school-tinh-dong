package com.example.websitemanageschooltinhdong.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String status;
    private String tokenType = "Bearer";

    public LoginResponse(String accessToken, String status) {
        this.accessToken = accessToken;
        this.status=status;
    }
}