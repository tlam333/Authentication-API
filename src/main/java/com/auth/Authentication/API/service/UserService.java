package com.auth.Authentication.API.service;

import com.auth.Authentication.API.model.User;
import com.auth.Authentication.API.repository.UserRepository;
import com.auth.Authentication.API.web.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*UserService: Business logic layer
Handles:
Hashing the password
creating a new user object
Saving it to DB

*/

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest req){
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword())); // <-- hash password
        userRepository.save(user);
    }
}
