package com.auth.Authentication.API.web;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;

}
