package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.dto.PaintingCreateDTO;
import com.ihor.artist_portfolio_server.dto.PaintingResponseDTO;
import com.ihor.artist_portfolio_server.dto.PaintingUpdateDTO;
import com.ihor.artist_portfolio_server.mapper.PaintingMapper;
import com.ihor.artist_portfolio_server.model.enums.PaintingStatus;
import com.ihor.artist_portfolio_server.service.PaintingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paintings")
@Tag(name = "Paintings", description = "Painting catalog management")
public class PaintingController {

    private final PaintingService paintingService;
    private final PaintingMapper paintingMapper;

    @Operation(summary = "Create a painting", description = "Requires ROLE_ARTIST")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<PaintingResponseDTO> createPainting(@RequestBody PaintingCreateDTO paintingCreateDTO) {
        return ResponseEntity.ok(paintingMapper.toDTO(paintingService.createPainting(paintingCreateDTO)));
    }

    @Operation(summary = "List all paintings")
    @GetMapping
    public ResponseEntity<List<PaintingResponseDTO>> getAllPaintings() {
        return ResponseEntity.ok(paintingService.getAllPaintings().stream().map(paintingMapper::toDTO).toList());
    }

    @Operation(summary = "Get painting by ID")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/{id}")
    public ResponseEntity<PaintingResponseDTO> getPaintingById(@PathVariable String id) {
        return ResponseEntity.ok(paintingMapper.toDTO(paintingService.getPaintingById(id)));
    }

    @Operation(summary = "Update painting", description = "Requires ROLE_ARTIST")
    @ApiResponse(responseCode = "404", description = "Not found")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<PaintingResponseDTO> updatePainting(@PathVariable String id, @RequestBody PaintingUpdateDTO paintingUpdateDTO) {
        return ResponseEntity.ok(paintingMapper.toDTO(paintingService.updatePainting(id, paintingUpdateDTO)));
    }

    @Operation(summary = "Delete painting", description = "Requires ROLE_ARTIST. Also removes all associated favorites")
    @ApiResponse(responseCode = "404", description = "Not found")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePainting(@PathVariable String id) {
        paintingService.deletePainting(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Filter paintings by status and/or price range")
    @GetMapping("/filter")
    public ResponseEntity<List<PaintingResponseDTO>> getPaintingsByStatusAndPriceRange(
            @RequestParam(required = false) PaintingStatus status,
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax) {
        return ResponseEntity.ok(paintingService.getPaintingsByStatusAndPriceBetween(status, priceMin, priceMax).stream().map(paintingMapper::toDTO).toList());
    }
}
