import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Campaign } from '../model/campaign.model';

@Injectable({
  providedIn: 'root'
})
export class CampaignService {
  private apiUrl = 'http://localhost:8080/api/campaign';

  constructor(private http: HttpClient) {}

  getCampaign(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(this.apiUrl);
  }
}
