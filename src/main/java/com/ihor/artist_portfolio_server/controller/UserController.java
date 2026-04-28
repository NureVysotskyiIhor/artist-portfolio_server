package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.dto.UserResponseDTO;
import com.ihor.artist_portfolio_server.dto.UserUpdateDTO;
import com.ihor.artist_portfolio_server.mapper.UserMapper;
import com.ihor.artist_portfolio_server.service.AuthService;
import com.ihor.artist_portfolio_server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User account management")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Get current authenticated user", description = "userId resolved from JWT")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getUserMe(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(userMapper.toDTO(userService.getUserById(userId)));
    }

    @Operation(summary = "Get user by ID")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userMapper.toDTO(userService.getUserById(id)));
    }

    @Operation(summary = "Update user profile")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String id, @RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.ok(userMapper.toDTO(userService.updateUser(id, userUpdateDTO)));
    }

    @Operation(summary = "Delete user account", description = "Also removes all user favorites")
    @ApiResponse(responseCode = "404", description = "Not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
