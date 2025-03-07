package com.futurum.excercise.services;

import com.futurum.excercise.model.Campaign;
import com.futurum.excercise.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public List<Campaign> fetchAllCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public Optional<Campaign> fetchCampaignById(Long campaignId) {
        return campaignRepository.findById(campaignId);
    }

    @Override
    public Campaign saveCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign updateCampaign(Long campaignId, Campaign campaign) {
        if(campaignRepository.findById(campaignId).isEmpty()){
            return null;
        }
        campaign.setId(campaignId);
        return campaignRepository.save(campaign);
    }

    @Override
    public boolean deleteCampaignById(Long campaignId) {
        if(campaignRepository.findById(campaignId).isEmpty()){
            return false;
        }
        campaignRepository.deleteById(campaignId);
        return true;
    }
}
