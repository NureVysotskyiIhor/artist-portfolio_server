package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.dto.CommissionTopicDTO;
import com.ihor.artist_portfolio_server.dto.CommissionTopicResponseDTO;
import com.ihor.artist_portfolio_server.mapper.CommissionTopicMapper;
import com.ihor.artist_portfolio_server.service.CommissionTopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commission-topics")
@Tag(name = "Commission Topics", description = "Lookup and management of commission topics")
public class CommissionTopicController {

    private final CommissionTopicService commissionTopicService;
    private final CommissionTopicMapper commissionTopicMapper;

    @Operation(summary = "List all commission topics")
    @GetMapping
    public ResponseEntity<List<CommissionTopicResponseDTO>> getAllCommissionTopics() {
        return ResponseEntity.ok(commissionTopicService.getAllCommissionTopics().stream().map(commissionTopicMapper::toDTO).toList());
    }

    @Operation(summary = "Get commission topic by ID")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/{id}")
    public ResponseEntity<CommissionTopicResponseDTO> getCommissionTopicById(@PathVariable String id) {
        return ResponseEntity.ok(commissionTopicMapper.toDTO(commissionTopicService.getCommissionTopicById(id)));
    }

    @Operation(summary = "Create a commission topic")
    @PostMapping
    public ResponseEntity<CommissionTopicResponseDTO> createCommissionTopic(@RequestBody CommissionTopicDTO commissionTopicDTO) {
        return ResponseEntity.ok(commissionTopicMapper.toDTO(commissionTopicService.createCommissionTopic(commissionTopicDTO)));
    }

    @Operation(summary = "Update a commission topic")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PutMapping("/{id}")
    public ResponseEntity<CommissionTopicResponseDTO> updateCommissionTopic(@RequestBody CommissionTopicDTO commissionTopicDTO, @PathVariable String id) {
        return ResponseEntity.ok(commissionTopicMapper.toDTO(commissionTopicService.updateCommissionTopic(id, commissionTopicDTO)));
    }

    @Operation(summary = "Delete a commission topic")
    @ApiResponse(responseCode = "404", description = "Not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommissionTopic(@PathVariable String id) {
        commissionTopicService.deleteCommissionTopic(id);
        return ResponseEntity.noContent().build();
    }
}
