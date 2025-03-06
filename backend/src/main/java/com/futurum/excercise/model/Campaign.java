package com.futurum.excercise.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @Column(nullable = false)
    private List<String> keywords;

    @Column(nullable = false)
    private int bidAmount;

    @Column(nullable = false)
    private int campaignFund;

    @Column(nullable = false)
    private boolean status;

    private String town;
    private int radius;

    public Campaign(int radius, String town, boolean status, int campaignFund, int bidAmount, List<String> keywords, String name, Long id) {
        this.radius = radius;
        this.town = town;
        this.status = status;
        this.campaignFund = campaignFund;
        this.bidAmount = bidAmount;
        this.keywords = keywords;
        this.name = name;
        this.id = id;
    }

    public Campaign() {
        keywords = List.of();
        name = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    public int getCampaignFund() {
        return campaignFund;
    }

    public void setCampaignFund(int campaignFund) {
        this.campaignFund = campaignFund;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
