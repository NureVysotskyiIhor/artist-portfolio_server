package com.ihor.artist_portfolio_server.dto;

import com.ihor.artist_portfolio_server.model.enums.PaintingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Payload for creating a painting")
public class PaintingCreateDTO {

    @Schema(example = "Starry Night")
    private String title;

    @Schema(example = "Oil on canvas, 73.7 × 92.1 cm")
    private String description;

    @Schema(description = "Public URL of the painting image")
    private String imageUrl;

    @Schema(description = "Price in USD", example = "1500.00")
    private Double price;

    @Schema(description = "Availability status", allowableValues = {"FOR_SALE", "SOLD", "NOT_FOR_SALE"})
    private PaintingStatus status;

    @Schema(description = "Whether the painting is publicly visible")
    private Boolean isPublic;
}
