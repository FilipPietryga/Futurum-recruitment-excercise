package com.futurum.excercise.services;

import com.futurum.excercise.model.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignService {
    List<Campaign> fetchAllCampaigns();
    Optional<Campaign> fetchCampaignById(Long campaignId);
    Campaign saveCampaign(Campaign campaign);
    Campaign updateCampaign(Long campaignId, Campaign campaign);
    boolean deleteCampaignById(Long departmentId);
}
