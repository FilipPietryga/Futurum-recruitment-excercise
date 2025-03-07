import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CampaignService } from '../../services/campaign/campaign.service';
import { Campaign } from '../../models/campaign/campaign.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-campaign-list',
  standalone: true,
  templateUrl: './campaign-list.component.html',
  styleUrls: ['./campaign-list.component.sass'],
  imports: [CommonModule]
})
export class CampaignListComponent implements OnInit {
  @Input() campaigns: Campaign[] = [];
  @Input() towns: string[] = [];
  @Output() delete = new EventEmitter<number>();
  @Output() edit = new EventEmitter<Campaign>();
  @Output() untrigger = new EventEmitter<boolean>();

  constructor(private campaignService: CampaignService) {}

  ngOnInit(): void {
    this.campaignService.getAllCampaigns().subscribe((data) => {
      this.campaigns = data;
    });
    console.log(this.campaigns);
  }
  
  onDelete(id: number): void {
    this.delete.emit(id); 
  }

  onEdit(campaign: Campaign): void {
    this.edit.emit(campaign); 
  }

  onUntrigger(): void {
    this.untrigger.emit(true);
  }
}
