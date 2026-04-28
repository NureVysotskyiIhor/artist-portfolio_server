package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.dto.HomepageProfileCreateDTO;
import com.ihor.artist_portfolio_server.dto.HomepageProfileResponseDTO;
import com.ihor.artist_portfolio_server.dto.HomepageProfileUpdateDTO;
import com.ihor.artist_portfolio_server.mapper.HomepageProfileMapper;
import com.ihor.artist_portfolio_server.model.HomepageProfile;
import com.ihor.artist_portfolio_server.service.HomepageProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/homepage-profile")
@Tag(name = "Homepage Profile", description = "Artist homepage profile")
public class HomepageProfileController {

    private final HomepageProfileService homepageProfileService;
    private final HomepageProfileMapper homepageProfileMapper;

    @Operation(summary = "Get the active homepage profile", description = "Returns 204 if no profile exists")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profile found"),
        @ApiResponse(responseCode = "204", description = "No profile exists yet")
    })
    @GetMapping
    public ResponseEntity<HomepageProfileResponseDTO> getHomepageProfile() {
        HomepageProfile profile = homepageProfileService.getHomepageProfile();
        if (profile == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(homepageProfileMapper.toDTO(profile));
    }

    @Operation(summary = "Update homepage profile")
    @ApiResponse(responseCode = "404", description = "No profile found to update")
    @PutMapping
    public ResponseEntity<HomepageProfileResponseDTO> updateHomepageProfile(@RequestBody HomepageProfileUpdateDTO homepageProfileUpdateDTO) {
        return ResponseEntity.ok(homepageProfileMapper.toDTO(homepageProfileService.updateHomepageProfile(homepageProfileUpdateDTO)));
    }

    @Operation(summary = "Create homepage profile")
    @PostMapping
    public ResponseEntity<HomepageProfileResponseDTO> createHomepageProfile(@RequestBody HomepageProfileCreateDTO homepageProfileCreateDTO) {
        return ResponseEntity.ok(homepageProfileMapper.toDTO(homepageProfileService.createHomepageProfile(homepageProfileCreateDTO)));
    }
}
