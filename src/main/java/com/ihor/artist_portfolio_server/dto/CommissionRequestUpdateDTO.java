package com.ihor.artist_portfolio_server.dto;

import com.ihor.artist_portfolio_server.model.Contacts;
import com.ihor.artist_portfolio_server.model.enums.CommissionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionRequestUpdateDTO {
    private String description;
    private Double budgetMin;
    private Double budgetMax;
    private LocalDate deadline;
    private Contacts contacts;
    private CommissionStatus status;
}
