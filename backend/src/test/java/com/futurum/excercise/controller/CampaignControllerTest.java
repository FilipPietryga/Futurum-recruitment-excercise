package com.futurum.excercise.controller;

import com.futurum.excercise.model.Campaign;
import com.futurum.excercise.services.CampaignService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CampaignControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CampaignService campaignService;

    @InjectMocks
    private CampaignController campaignController;

    private Campaign campaign;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(campaignController).build();
        campaign = new Campaign(1L, "Spring Boot Launch", "java,spring,boot", 100, 1000, true, "Krakow", 50);
    }

    @Test
    public void getAllCampaignsSuccessfully() throws Exception {
        when(campaignService.fetchAllCampaigns()).thenReturn(Arrays.asList(campaign));

        mockMvc.perform(get("/api/campaign"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(campaign.getId()))
            .andExpect(jsonPath("$[0].name").value(campaign.getName()));
    }

    @Test
    public void getAllCampaignsNotFound() throws Exception {
        when(campaignService.fetchAllCampaigns()).thenReturn(null);

        mockMvc.perform(get("/api/campaign"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCampaignByIdSuccessfully() throws Exception {
        when(campaignService.fetchCampaignById(1L)).thenReturn(Optional.of(campaign));

        mockMvc.perform(get("/api/campaign/{campaignId}", 1L))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(campaign.getId()))
            .andExpect(jsonPath("$.name").value(campaign.getName()));
    }

    @Test
    public void getCampaignByIdNotFound() throws Exception {
        when(campaignService.fetchCampaignById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/campaign/{campaignId}", 1L))
            .andExpect(status().isNotFound());
    }

    @Test
    public void createCampaignSuccessfully() throws Exception {
        when(campaignService.saveCampaign(any(Campaign.class))).thenReturn(campaign);

        mockMvc.perform(post("/api/campaign")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(campaign)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(campaign.getId()))
            .andExpect(jsonPath("$.name").value(campaign.getName()));
    }

    @Test
    public void updateCampaignSuccessfully() throws Exception {
        Campaign updatedCampaign = new Campaign(1L, "Updated Campaign", "Updated Keyword", 200, 2000, false, "Updated Town", 100);
        when(campaignService.updateCampaign(eq(1L), any(Campaign.class))).thenReturn(updatedCampaign);

        mockMvc.perform(put("/api/campaign/{campaignId}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(updatedCampaign)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(updatedCampaign.getName()))
            .andExpect(jsonPath("$.keywords").value(updatedCampaign.getKeywords()));
    }

    @Test
    public void updateCampaignNotFound() throws Exception {
        Campaign updatedCampaign = new Campaign(1L, "Updated Campaign", "Updated Keyword", 200, 2000, false, "Updated Town", 100);
        when(campaignService.updateCampaign(eq(1L), any(Campaign.class))).thenReturn(null);

        mockMvc.perform(put("/api/campaign/{campaignId}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(updatedCampaign)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCampaignSuccessfully() throws Exception {
        when(campaignService.deleteCampaignById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/campaign/{campaignId}", 1L))
            .andExpect(status().isNoContent());
    }

    @Test
    public void deleteCampaignNotFound() throws Exception {
        when(campaignService.deleteCampaignById(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/campaign/{campaignId}", 1L))
            .andExpect(status().isNotFound());
    }
}
