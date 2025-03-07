import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Campaign } from '../../models/campaign/campaign.model';

@Injectable({
  providedIn: 'root'
})
export class CampaignService {
  private campaignApiUrl = 'http://localhost:8080/api/campaign';

  constructor(private http: HttpClient) {}

  getAllCampaigns(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(this.campaignApiUrl);
  }

  getCampaignById(campaignId: number): Observable<Campaign> {
    return this.http.get<Campaign>(`${this.campaignApiUrl}/${campaignId}`);
  }

  createCampaign(campaign: Campaign): Observable<Campaign> {
    return this.http.post<Campaign>(this.campaignApiUrl, campaign);
  }

  updateCampaign(campaignId: number, campaign: Campaign): Observable<Campaign> {
    return this.http.put<Campaign>(`${this.campaignApiUrl}/${campaignId}`, campaign);
  }

  deleteCampaign(campaignId: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.campaignApiUrl}/${campaignId}`);
  }
}
