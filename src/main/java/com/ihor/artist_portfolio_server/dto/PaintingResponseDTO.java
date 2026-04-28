package com.ihor.artist_portfolio_server.dto;

import com.ihor.artist_portfolio_server.model.enums.PaintingStatus;
import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PaintingResponseDTO {
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private PaintingStatus status;
    private Boolean isPublic;
    private LocalDateTime createdAt;
}
