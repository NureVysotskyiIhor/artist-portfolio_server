package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.dto.LoginRequest;
import com.ihor.artist_portfolio_server.dto.LoginResponse;
import com.ihor.artist_portfolio_server.dto.UserCreateDTO;
import com.ihor.artist_portfolio_server.dto.UserResponseDTO;
import com.ihor.artist_portfolio_server.mapper.UserMapper;
import com.ihor.artist_portfolio_server.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Registration, login, and email verification")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @Operation(summary = "Register a new user", description = "Creates user, sends verification email")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User created, verification email sent"),
        @ApiResponse(responseCode = "400", description = "Email already exists")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserCreateDTO userCreateDTO) {
        return ResponseEntity.ok(userMapper.toDTO(authService.register(userCreateDTO)));
    }

    @Operation(summary = "Verify email address", description = "Consumes single-use token sent by email")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Email verified successfully"),
        @ApiResponse(responseCode = "400", description = "Token not found or expired")
    })
    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String token) {
        authService.verifyToken(token);
        return ResponseEntity.ok("Email verified successfully");
    }

    @Operation(summary = "Authenticate user", description = "Returns JWT token on success")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "JWT returned"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials or user not found"),
        @ApiResponse(responseCode = "403", description = "Email not verified")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request.getEmail(), request.getPassword()));
    }
}
