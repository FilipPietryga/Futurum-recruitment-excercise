import { Component, OnInit } from '@angular/core';
import { CampaignService } from '../../services/campaign/campaign.service';
import { Campaign } from '../../models/campaign/campaign.model';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CampaignListComponent } from "../campaign-list/campaign-list.component";

@Component({
  selector: 'app-campaign-management',
  templateUrl: './campaign-management.component.html',
  styleUrls: ['./campaign-management.component.sass'],
  imports: [ReactiveFormsModule, CommonModule, CampaignListComponent],
  standalone: true
})
export class CampaignManagementComponent implements OnInit {
  campaigns: Campaign[] = [];
  campaignForm: FormGroup;
  selectedCampaign: Campaign | null = null;
  towns: string[] = ['Warsaw', 'Krakow', 'Gdansk', 'Wroclaw', 'Poznan', 'Lodz'];
  selectedTown: string | null = null;
  
  constructor(private campaignService: CampaignService, private fb: FormBuilder) {
    this.campaignForm = this.fb.group({
      id: [null],
      name: ['', Validators.required],
      bidAmount: [0, Validators.required],
      campaignFund: [0, Validators.required],
      status: [false],
      town: [''],
      radius: [0],
      keywords: ['']
    });
  }

  ngOnInit(): void {
    this.fetchAllCampaigns();
  }

  fetchAllCampaigns(): void {
    this.campaignService.getAllCampaigns().subscribe(
      (campaigns) => {
        this.campaigns = campaigns;
      },
      (error) => {
        console.error('Error fetching campaigns:', error);
      }
    );
  }

  fetchCampaign(campaignId: number): void {
    this.campaignService.getCampaignById(campaignId).subscribe(
      (campaign) => {
        this.campaigns = [campaign];
      },
      (error) => {
        console.error('Error fetching campaign:', error);
      }
    );
  }

  submitCampaign(): void {
    if (this.campaignForm.invalid) {
      return;
    }

    const campaignData = this.campaignForm.value;
    if (campaignData.id) {
      this.updateCampaign(campaignData);
    } else {
      this.createCampaign(campaignData);
    }
  }

  createCampaign(campaignData: Campaign): void {
    this.campaignService.createCampaign(campaignData).subscribe(
      (newCampaign) => {
        this.campaigns.push(newCampaign);
        this.campaignForm.reset();
      },
      (error) => {
        console.error('Error creating campaign:', error);
      }
    );
  }

  updateCampaign(campaignData: Campaign): void {
    this.campaignService.updateCampaign(campaignData.id!, campaignData).subscribe(
      (updatedCampaign) => {
        const index = this.campaigns.findIndex(c => c.id === updatedCampaign.id);
        if (index !== -1) {
          this.campaigns[index] = updatedCampaign;
        }
        this.campaignForm.reset();
      },
      (error) => {
        console.error('Error updating campaign:', error);
      }
    );
  }

  deleteCampaign(campaignId: number): void {
    this.campaignService.deleteCampaign(campaignId).subscribe(
      () => {
        this.campaigns = this.campaigns.filter(c => c.id !== campaignId);
      },
      (error) => {
        console.error('Error deleting campaign:', error);
      }
    );
  }

  editCampaign(campaign: Campaign): void {
    this.selectedCampaign = campaign;
    this.campaignForm.patchValue(campaign);
  }

  untriggerCampaign(): void {
    this.selectedCampaign = null;
    this.campaignForm.reset();
  }
}
