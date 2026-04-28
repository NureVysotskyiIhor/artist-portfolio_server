package com.ihor.artist_portfolio_server.dto;

import com.ihor.artist_portfolio_server.model.Contacts;
import com.ihor.artist_portfolio_server.model.enums.CommissionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Payload for submitting a commission request")
public class CommissionRequestCreateDTO {

    @Schema(description = "ID of the requesting user")
    private String userId;

    @Schema(description = "ID of the commission topic")
    private String topicId;

    @Schema(example = "Portrait for wedding")
    private String title;

    @Schema(example = "Oil portrait, A4, romantic style")
    private String description;

    @Schema(description = "Minimum acceptable budget (USD)", example = "200.0")
    private Double budgetMin;

    @Schema(description = "Maximum acceptable budget (USD)", example = "500.0")
    private Double budgetMax;

    @Schema(description = "Desired completion date", example = "2026-06-01")
    private LocalDate deadline;

    private Contacts contacts;

    private CommissionStatus status;

    @Schema(description = "City for geo-tagging", example = "Kyiv")
    private String city;

    @Schema(description = "Longitude for geo search", example = "30.5238")
    private Double longitude;

    @Schema(description = "Latitude for geo search", example = "50.4501")
    private Double latitude;
}
