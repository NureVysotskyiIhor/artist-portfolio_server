package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.dto.CommissionRequestCreateDTO;
import com.ihor.artist_portfolio_server.dto.CommissionRequestResponseDTO;
import com.ihor.artist_portfolio_server.dto.CommissionRequestUpdateArtistDTO;
import com.ihor.artist_portfolio_server.dto.CommissionRequestUpdateDTO;
import com.ihor.artist_portfolio_server.mapper.CommissionRequestMapper;
import com.ihor.artist_portfolio_server.service.CommissionRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commission-requests")
@RequiredArgsConstructor
@Tag(name = "Commission Requests", description = "Commission request CRUD and geo search")
public class CommissionRequestController {

    private final CommissionRequestService commissionRequestService;
    private final CommissionRequestMapper commissionRequestMapper;

    @Operation(summary = "List all commission requests")
    @GetMapping
    public ResponseEntity<List<CommissionRequestResponseDTO>> getAllCommissionRequests() {
        return ResponseEntity.ok(
                commissionRequestService.getAllCommissionRequests()
                        .stream()
                        .map(commissionRequestMapper::toDTO)
                        .toList()
        );
    }

    @Operation(summary = "Get commission request by ID")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/{id}")
    public ResponseEntity<CommissionRequestResponseDTO> getCommissionRequest(@PathVariable String id) {
        return ResponseEntity.ok(commissionRequestMapper.toDTO(commissionRequestService.getCommissionRequestsById(id)));
    }

    @Operation(summary = "Get commission requests by user")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommissionRequestResponseDTO>> getByUserId(
            @PathVariable String userId) {
        return ResponseEntity.ok(
                commissionRequestService.getCommissionRequestsByUserId(userId)
                        .stream()
                        .map(commissionRequestMapper::toDTO)
                        .toList()
        );
    }

    @Operation(summary = "Find requests near a geographic location", description = "Uses 2dsphere geo index; radius in kilometres")
    @GetMapping("/nearby")
    public ResponseEntity<List<CommissionRequestResponseDTO>> findByRadiusKm(
            @RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double radiusKm) {
        return ResponseEntity.ok(
                commissionRequestService.findByRadiusKm(latitude, longitude, radiusKm)
                        .stream()
                        .map(commissionRequestMapper::toDTO)
                        .toList()
        );
    }

    @Operation(summary = "Create a new commission request")
    @PostMapping
    public ResponseEntity<CommissionRequestResponseDTO> createCommissionRequest(
            @RequestBody CommissionRequestCreateDTO dto) {
        return ResponseEntity.ok(
                commissionRequestMapper.toDTO(
                        commissionRequestService.createCommissionRequest(dto)
                )
        );
    }

    @Operation(summary = "Update commission request (client)")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PutMapping("/{id}")
    public ResponseEntity<CommissionRequestResponseDTO> updateCommissionRequest(
            @PathVariable String id,
            @RequestBody CommissionRequestUpdateDTO dto) {
        return ResponseEntity.ok(
                commissionRequestMapper.toDTO(
                        commissionRequestService.updateCommissionRequest(dto, id)
                )
        );
    }

    @Operation(summary = "Update commission request (artist)", description = "Restricted to ROLE_ARTIST. Updates status and artist note only")
    @ApiResponse(responseCode = "404", description = "Not found")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/artist")
    public ResponseEntity<CommissionRequestResponseDTO> updateByArtist(
            @PathVariable String id,
            @RequestBody CommissionRequestUpdateArtistDTO dto) {

        return ResponseEntity.ok(
                commissionRequestMapper.toDTO(
                        commissionRequestService.updateCommissionRequestByArtist(dto, id)
                )
        );
    }

    @Operation(summary = "Delete commission request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommissionRequest(@PathVariable String id) {
        commissionRequestService.deleteCommissionRequest(id);
        return ResponseEntity.noContent().build();
    }
}
