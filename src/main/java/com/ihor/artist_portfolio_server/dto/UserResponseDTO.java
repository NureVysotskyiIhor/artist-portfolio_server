package com.ihor.artist_portfolio_server.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String email;
    private String username;
    private String avatarUrl;
    private String bio;
}
