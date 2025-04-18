package com.auth.Authentication.API.controller;

import com.auth.Authentication.API.service.UserService;
import com.auth.Authentication.API.web.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
This is a REST controller that handles HTTP requests:
-Accepts a POST request
-Converts the JSON to RegisterRequest
-Passes it to the service layer
*/

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req){
        userService.register(req);
        return ResponseEntity.ok("User Registered");
    }
}
