package org.nicolas.optimaltech.service;

import lombok.RequiredArgsConstructor;
import org.nicolas.optimaltech.dto.AuthResponse;
import org.nicolas.optimaltech.dto.LoginRequest;
import org.nicolas.optimaltech.dto.RegisterRequest;
import org.nicolas.optimaltech.entity.Role;
import org.nicolas.optimaltech.entity.User;
import org.nicolas.optimaltech.repository.RoleRepository;
import org.nicolas.optimaltech.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");

        if (roleOptional.isEmpty()) {
            throw new RuntimeException("Role not found");
        }

        Role role = roleOptional.get();
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
