package com.futurum.excercise.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    @GetMapping
    public List<String> getCampaign() {
        return List.of("Campaign #1", "Campaign #2", "Campaign #3");
    }
}
