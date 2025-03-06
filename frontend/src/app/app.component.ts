import { Component } from '@angular/core';
import { CampaignService } from './services/campaign.service';
import { CommonModule } from '@angular/common';
import { Campaign } from './model/campaign.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent {
  title: string = "frontend";
  campaigns: Campaign[] = [];

  constructor(private campaignService: CampaignService) {}

  ngOnInit(): void {
    this.campaignService.getCampaign().subscribe(data => {
      this.campaigns = data;
    });
  }
}
