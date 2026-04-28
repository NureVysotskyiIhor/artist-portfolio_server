package com.ihor.artist_portfolio_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FavoriteResponseDTO {
    private String id;
    private String userId;
    private String paintingId;
    private LocalDateTime createdAt;
}
