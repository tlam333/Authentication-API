package com.auth.Authentication.API.web;

import lombok.Data;

//DTO (Data Transfer Object)
//It’s a plain Java object used to carry data between client and server, without exposing internal models like User
@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
