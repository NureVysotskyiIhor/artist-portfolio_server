package com.ihor.artist_portfolio_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommissionTopicResponseDTO {
    private String id;
    private String name;
    private String description;
    private String icon;
    private Boolean isActive;
}
