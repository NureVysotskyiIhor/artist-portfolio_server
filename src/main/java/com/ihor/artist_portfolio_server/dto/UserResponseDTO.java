package com.ihor.artist_portfolio_server.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String email;
    private String name;
    private String avatarUrl;
    private String bio;
    private Boolean isVerified;
    private LocalDateTime createdAt;
}
