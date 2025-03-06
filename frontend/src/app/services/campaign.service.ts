import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CampaignService {
  private apiUrl = 'http://localhost:8080/api/campaign';

  constructor(private http: HttpClient) {}

  getCampaign(): Observable<string[]> {
    return this.http.get<string[]>(this.apiUrl);
  }
}
