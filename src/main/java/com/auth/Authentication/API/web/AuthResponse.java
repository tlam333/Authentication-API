package com.auth.Authentication.API.web;

import lombok.AllArgsConstructor;
import lombok.Data;

//Just a simple token wrapper

@Data
public class AuthResponse {
    private String token;

    public AuthResponse() {} // needed for Jackson

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
