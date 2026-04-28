package com.ihor.artist_portfolio_server.dto;

import com.ihor.artist_portfolio_server.model.enums.CommissionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Artist-side update for a commission request")
public class CommissionRequestUpdateArtistDTO {

    @Schema(description = "Internal note visible only to the artist")
    private String artistNote;

    @Schema(description = "New status", allowableValues = {"PENDING", "ACCEPTED", "REJECTED", "IN_PROGRESS", "ON_REVIEW", "COMPLETED", "CANCELLED", "EXPIRED"})
    private CommissionStatus status;
}
