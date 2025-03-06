import { Component } from '@angular/core';
import { CampaignService } from './services/campaign.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent {
  title: string = "frontend";
  campaigns: string[] = [];

  constructor(private campaignService: CampaignService) {}

  ngOnInit(): void {
    this.campaignService.getCampaign().subscribe(data => {
      this.campaigns = data;
    });
  }
}
