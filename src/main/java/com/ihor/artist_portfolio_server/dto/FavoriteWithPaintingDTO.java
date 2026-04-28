package com.ihor.artist_portfolio_server.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class FavoriteWithPaintingDTO {
    private  String id;
    private String userId;
    private String paintingId;
    private LocalDateTime createdAt;
    private PaintingResponseDTO painting;
}
