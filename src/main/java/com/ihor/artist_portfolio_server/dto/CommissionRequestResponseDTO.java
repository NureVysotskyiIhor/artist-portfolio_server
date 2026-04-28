package com.ihor.artist_portfolio_server.dto;

import com.ihor.artist_portfolio_server.model.Contacts;
import com.ihor.artist_portfolio_server.model.enums.CommissionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CommissionRequestResponseDTO {
    private String id;
    private String userId;
    private String topicId;
    private String topicName;
    private String title;
    private String description;
    private Double budgetMin;
    private Double budgetMax;
    private LocalDate deadline;
    private Contacts contacts;
    private String artistNote;
    private CommissionStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String city;
    private Double longitude;
    private Double latitude;
}
