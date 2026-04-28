package com.ihor.artist_portfolio_server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Payload for user registration")
public class UserCreateDTO {

    @Schema(description = "Email address (must be unique)", example = "user@example.com")
    private String email;

    @Schema(description = "Plain-text password (hashed before storage)", example = "Str0ngP@ss!")
    private String password;

    @Schema(description = "Display name", example = "John Doe")
    private String name;

    @Schema(description = "URL of profile avatar", example = "https://cdn.example.com/avatar.png")
    private String avatarUrl;

    @Schema(description = "Short user biography")
    private String bio;
}
