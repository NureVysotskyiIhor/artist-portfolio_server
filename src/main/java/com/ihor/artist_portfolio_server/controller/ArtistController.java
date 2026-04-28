package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.dto.ArtistCreateDTO;
import com.ihor.artist_portfolio_server.dto.ArtistResponseDTO;
import com.ihor.artist_portfolio_server.dto.ArtistUpdateDTO;
import com.ihor.artist_portfolio_server.mapper.ArtistMapper;
import com.ihor.artist_portfolio_server.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artist")
@RequiredArgsConstructor
@Tag(name = "Artist", description = "Artist profile management")
public class ArtistController {

    private final ArtistService artistService;
    private final ArtistMapper artistMapper;

    @Operation(summary = "Get artist by ID")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> getArtistById(@PathVariable String id) {
        return ResponseEntity.ok(artistMapper.toDTO(artistService.getArtistById(id)));
    }

    @Operation(summary = "Create a new artist profile")
    @PostMapping
    public ResponseEntity<ArtistResponseDTO> createArtist(@RequestBody ArtistCreateDTO artistCreateDTO) {
        return ResponseEntity.ok(artistMapper.toDTO(artistService.createArtist(artistCreateDTO)));
    }

    @Operation(summary = "Update artist profile")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> updateArtist(@PathVariable String id, @RequestBody ArtistUpdateDTO artistUpdateDTO) {
        return ResponseEntity.ok(artistMapper.toDTO(artistService.updateArtist(id, artistUpdateDTO)));
    }
}
