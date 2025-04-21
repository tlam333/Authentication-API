package com.auth.Authentication.API.web;

import lombok.AllArgsConstructor;
import lombok.Data;

//Just a simple token wrapper

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
