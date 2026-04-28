package com.ihor.artist_portfolio_server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Successful login response")
public class LoginResponse {

    @Schema(description = "JWT bearer token")
    private String token;

    @Schema(description = "True if the authenticated user has an artist profile")
    private Boolean isArtist;
}
