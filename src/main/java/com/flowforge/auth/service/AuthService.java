package com.flowforge.auth.service;

import com.flowforge.auth.domain.User;
import com.flowforge.auth.dto.AuthResponse;
import com.flowforge.auth.dto.LoginRequest;
import com.flowforge.auth.dto.RegisterRequest;
import com.flowforge.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public AuthResponse register(RegisterRequest request){
        String encoded = passwordEncoder.encode(request.password());
        User user = new User(request.email(), encoded);

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid ceredentials"));

        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

}
