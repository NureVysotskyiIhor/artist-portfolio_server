package com.ihor.artist_portfolio_server.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ArtistResponseDTO {
    private String id;
    private String email;
}
