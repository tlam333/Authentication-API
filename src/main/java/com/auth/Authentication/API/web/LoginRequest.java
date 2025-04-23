package com.auth.Authentication.API.web;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;

    //Even tho im using lombok im creating these constructors cause intellij not recognisin lombok for now
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
