import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CampaignListComponent } from './campaignList.component';
import { provideHttpClient } from '@angular/common/http';

describe('CampaignListComponent', () => {
  let component: CampaignListComponent;
  let fixture: ComponentFixture<CampaignListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CampaignListComponent],
      providers: [
        provideHttpClient()
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CampaignListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('Should be created', () => {
    expect(component).toBeTruthy();
  });
});
