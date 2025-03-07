import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { CampaignService } from './campaign.service';
import { provideHttpClient } from '@angular/common/http';
import { Campaign } from '../../models/campaign/campaign.model';
import { response } from 'express';

describe('CampaignService', () => {
  let service: CampaignService;
  let httpMock: HttpTestingController;

  const campaignApiUrl = 'http://localhost:8080/api/campaign';

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

  it('Should fetch all campaigns', () => {
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

    const req = httpMock.expectOne(campaignApiUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockCampaigns);
  });

  it('Should fetch a campaign by ID', () => {
    const mockCampaign: Campaign = {
      id: 1,
      name: 'Campaign 1',
      bidAmount: 0,
      campaignFund: 0,
      status: false,
      town: '',
      radius: 0,
      keywords: ''
    };

    service.getCampaignById(1).subscribe(campaign => {
      expect(campaign).toEqual(mockCampaign);
    });

    const req = httpMock.expectOne(`${campaignApiUrl}/1`);
    expect(req.request.method).toBe('GET');
    req.flush(mockCampaign);
  });

  it('Should create a new campaign', () => {
    const newCampaign: Campaign = {
      id: 0,
      name: 'New Campaign',
      bidAmount: 100,
      campaignFund: 500,
      status: true,
      town: 'Test Town',
      radius: 50,
      keywords: 'test, new'
    };

    const mockCreatedCampaign: Campaign = { ...newCampaign, id: 3 };

    service.createCampaign(newCampaign).subscribe(campaign => {
      expect(campaign).toEqual(mockCreatedCampaign);
    });

    const req = httpMock.expectOne(campaignApiUrl);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newCampaign);
    req.flush(mockCreatedCampaign);
  });

  it('Should update an existing campaign', () => {
    const updatedCampaign: Campaign = {
      id: 1,
      name: 'Updated Campaign',
      bidAmount: 200,
      campaignFund: 1000,
      status: true,
      town: 'Updated Town',
      radius: 100,
      keywords: 'updated, campaign'
    };

    service.updateCampaign(1, updatedCampaign).subscribe(campaign => {
      expect(campaign).toEqual(updatedCampaign);
    });

    const req = httpMock.expectOne(`${campaignApiUrl}/1`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updatedCampaign);
    req.flush(updatedCampaign);
  });

  it('Should delete a campaign by ID', () => {
    const campaignId = 1;

    service.deleteCampaign(campaignId).subscribe(response => {
      expect(response).toBeNull();
    });

    const req = httpMock.expectOne(`${campaignApiUrl}/1`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null, { status: 204, statusText: 'No Content' });
  });
});
