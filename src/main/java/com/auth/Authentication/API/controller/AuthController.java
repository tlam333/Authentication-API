package com.auth.Authentication.API.controller;

import com.auth.Authentication.API.config.Jwtutil;
import com.auth.Authentication.API.service.UserService;
import com.auth.Authentication.API.web.AuthResponse;
import com.auth.Authentication.API.web.LoginRequest;
import com.auth.Authentication.API.web.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

//@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final Jwtutil jwtUtil;

    // Manually define a constructor as Lombok is not recognised by Intellij for now
    public AuthController(UserService userService, PasswordEncoder passwordEncoder, Jwtutil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req){
        userService.register(req);
        return ResponseEntity.ok("User Registered");

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req){
        //Get user from database
        var user = userService.loadUserByUsername(req.getUsername());

        //Check Password
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        // Generate JSON Web Token (JWT)
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
