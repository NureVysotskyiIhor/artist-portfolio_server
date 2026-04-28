package com.ihor.artist_portfolio_server.dto;

import lombok.Data;

@Data
public class FavoritesPaintingStatsDTO {
    private String paintingId;
    private Long count;
}
