package com.futurum.excercise.controller;

import com.futurum.excercise.model.Campaign;
import com.futurum.excercise.services.CampaignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Campaign Management", description = "Operations related to campaigns")
@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    @Autowired
    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Tag(name = "Campaign Management")
    @Operation(summary = "Get all campaigns", description = "Retrieve a list of all campaigns.")
    @GetMapping
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        return ResponseEntity.ok(campaignService.fetchAllCampaigns());
    }

    @Tag(name = "Campaign Management")
    @Operation(summary = "Get campaign by ID", description = "Retrieve a campaign by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campaign found"),
            @ApiResponse(responseCode = "404", description = "Campaign not found")
    })
    @GetMapping("/{campaignId}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long campaignId) {
        Optional<Campaign> campaign = campaignService.fetchCampaignById(campaignId);
        return campaign.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Tag(name = "Campaign Management")
    @Operation(summary = "Create a new campaign", description = "Create a new campaign by providing campaign details.")
    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        Campaign savedCampaign = campaignService.saveCampaign(campaign);
        return new ResponseEntity<>(savedCampaign, HttpStatus.CREATED);
    }

    @Tag(name = "Campaign Management")
    @Operation(summary = "Update an existing campaign", description = "Update an existing campaign by providing updated details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campaign updated successfully"),
            @ApiResponse(responseCode = "404", description = "Campaign not found")
    })
    @PutMapping("/{campaignId}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Long campaignId, @RequestBody Campaign updatedCampaign) {
        Campaign savedCampaign = campaignService.updateCampaign(campaignId, updatedCampaign);
        return ResponseEntity.ok(savedCampaign);
    }

    @Tag(name = "Campaign Management")
    @Operation(summary = "Delete a campaign", description = "Delete a campaign by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Campaign deleted"),
            @ApiResponse(responseCode = "404", description = "Campaign not found")
    })
    @DeleteMapping("/{campaignId}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long campaignId) {
        if (!campaignService.deleteCampaignById(campaignId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
