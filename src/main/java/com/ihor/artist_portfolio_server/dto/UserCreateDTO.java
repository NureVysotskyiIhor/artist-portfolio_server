package com.ihor.artist_portfolio_server.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UserCreateDTO {
    private String email;
    private String username;
    private String avatarUrl;
    private String bio;
}
