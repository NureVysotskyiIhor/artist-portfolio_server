package com.ihor.artist_portfolio_server.dto;

import com.ihor.artist_portfolio_server.model.enums.PaintingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaintingUpdateDTO {
    private String title;
    private String description;
    private String imageUrl;
    private Double price ;
    private PaintingStatus status;
    private Boolean isPublic;
}
