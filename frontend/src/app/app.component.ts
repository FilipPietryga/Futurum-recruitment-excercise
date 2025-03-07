import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CampaignListComponent } from './component/campaignList/campaignList.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, CampaignListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent {
}
