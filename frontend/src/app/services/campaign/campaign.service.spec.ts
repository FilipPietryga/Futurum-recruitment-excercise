import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { CampaignService } from './campaign.service';
import { provideHttpClient } from '@angular/common/http';
import { Campaign } from '../../models/campaign/campaign.model';

describe('CampaignService', () => {
  let service: CampaignService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        CampaignService
      ]
    });

    service = TestBed.inject(CampaignService);
    httpMock = TestBed.inject(HttpTestingController);
  });
  
  afterEach(() => {
    httpMock.verify();
  });
  
  it('Should be created', () => {
    expect(service).toBeTruthy();
  });

  it('Should save campaigns', () => {
    const mockCampaigns: Campaign[] = [
      {
        id: 1, name: 'Campaign 1',
        bidAmount: 0,
        campaignFund: 0,
        status: false,
        town: '',
        radius: 0,
        keywords: ''
      },
      {
        id: 2, name: 'Campaign 2',
        bidAmount: 0,
        campaignFund: 0,
        status: false,
        town: '',
        radius: 0,
        keywords: ''
      }
    ];

    service.getAllCampaigns().subscribe(campaigns => {
      expect(campaigns.length).toBe(2); 
      expect(campaigns).toEqual(mockCampaigns); 
    });

    const req = httpMock.expectOne('http://localhost:8080/api/campaign');
    expect(req.request.method).toBe('GET'); 

    req.flush(mockCampaigns); 
  });
});
