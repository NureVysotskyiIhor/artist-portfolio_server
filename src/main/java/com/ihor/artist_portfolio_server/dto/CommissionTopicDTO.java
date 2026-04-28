package com.ihor.artist_portfolio_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionTopicDTO {
    private String name;
    private String description;
    private String icon;
    private Boolean isActive;
}
