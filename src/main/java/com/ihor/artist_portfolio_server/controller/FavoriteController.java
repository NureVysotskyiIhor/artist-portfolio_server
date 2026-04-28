package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.dto.*;
import com.ihor.artist_portfolio_server.mapper.FavoriteMapper;
import com.ihor.artist_portfolio_server.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
@Tag(name = "Favorites", description = "User painting favorites")
@SecurityRequirement(name = "bearerAuth")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final FavoriteMapper favoriteMapper;

    @Operation(summary = "Get user favorites (IDs only)")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteResponseDTO>> getFavorites(@PathVariable String userId) {
        return ResponseEntity.ok(favoriteService.getUserFavorites(userId).stream().map(favoriteMapper::toDTO).toList());
    }

    @Operation(summary = "Remove a painting from favorites", description = "userId resolved from JWT")
    @ApiResponse(responseCode = "404", description = "Favorite not found")
    @DeleteMapping
    public ResponseEntity<Void> deleteFavorite(@RequestBody FavoriteDeleteDTO favoriteDeleteDTO, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        favoriteService.removeFavorite(userId, favoriteDeleteDTO.getPaintingId());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a painting to favorites", description = "userId resolved from JWT")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Added to favorites"),
        @ApiResponse(responseCode = "409", description = "Already in favorites")
    })
    @PostMapping
    public ResponseEntity<FavoriteResponseDTO> createFavorite(@RequestBody FavoriteCreateDTO favoriteCreateDTO, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(favoriteMapper.toDTO(favoriteService.addFavorite(favoriteCreateDTO, userId)));
    }

    @Operation(summary = "Get favorites with full painting data", description = "Runs MongoDB $lookup aggregation. userId resolved from JWT")
    @GetMapping("/with-painting")
    public ResponseEntity<List<FavoriteWithPaintingDTO>> getFavoritesWithPainting(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(favoriteService.getFavoritesWithPainting(userId));
    }

    @Operation(summary = "Get favorite counts per painting")
    @GetMapping("/stats")
    public ResponseEntity<List<FavoritesPaintingStatsDTO>> getPaintingStats() {
        return ResponseEntity.ok(favoriteService.getFavoritesStats());
    }
}
