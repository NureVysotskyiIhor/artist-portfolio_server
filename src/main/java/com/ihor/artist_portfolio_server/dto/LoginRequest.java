package com.ihor.artist_portfolio_server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Authentication credentials")
public class LoginRequest {

    @Schema(description = "Registered email address", example = "artist@example.com")
    private String email;

    @Schema(description = "Account password", example = "hunter2")
    private String password;
}
