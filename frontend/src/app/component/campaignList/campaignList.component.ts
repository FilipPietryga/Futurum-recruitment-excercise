import { Component, OnInit } from '@angular/core';
import { CampaignService } from '../../services/campaign/campaign.service';
import { Campaign } from '../../models/campaign/campaign.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'campaign-list',
  standalone: true,
  templateUrl: './campaignList.component.html',
  styleUrls: ['./campaignList.component.sass'],
  imports: [CommonModule]
})
export class CampaignListComponent implements OnInit {
  campaigns: Campaign[] = [];

  constructor(private campaignService: CampaignService) {}

  ngOnInit(): void {
    this.campaignService.getAllCampaigns().subscribe((data) => {
      this.campaigns = data;
    });
    console.log(this.campaigns);
  }
}
