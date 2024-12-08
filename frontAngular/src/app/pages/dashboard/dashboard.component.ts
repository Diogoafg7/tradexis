import { Component,OnInit ,  } from '@angular/core';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../../components/header/header.component';
import { NgxApexchartsModule } from 'ngx-apexcharts';
import { AssetService } from '../../asset.service';
import { ProfileServiceService } from '../../profile-service.service';


@Component({
  selector: 'app-dashboard',
  imports: [NgFor, NgIf, FormsModule,  NgxApexchartsModule, HeaderComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  
})
export class DashboardComponent  {
 stocks: any[] = [ ];

  selectedStock: any = null;
  buyAmount: number = 0;


  constructor(
    private assetService: AssetService,
    private profileService: ProfileServiceService
  ) {}

  
  
  loadAssets(): void {
    this.assetService.getAssets().subscribe(
      (data) => {
        this.stocks = data;
        // .map((stock) => ({
        //   ...stock,
        //   id: stock.assetId, // Map `assetId` from backend to `id` for consistency
        // }));
        console.log('Fetched data:', this.stocks);
        
      },
      (error) => {
        console.error('Error fetching assets', error);
      }
    );
  }

  ngOnInit(): void {
    this.loadAssets();
    console.log('oninit' );
  }


  toggleBuySection(stock: any): void {
    this.selectedStock = this.selectedStock === stock ? null : stock;
  }

  confirmPurchase(stock: any): void {
    const userId = this.profileService.getCurrentUserId(); 
    //console.log('userid', userId );
    const assetId = stock.id; 
    //console.log('assetId', assetId );
    const tradeTypeName = 'BUY'; 
    const quantity = this.buyAmount;
  
    if (quantity <= 0) {
      console.error('Quantity must be greater than zero.');
      return;
    }
  
    this.assetService.addTrade(userId, assetId, tradeTypeName, quantity).subscribe(
      (response) => {
        console.log('Trade added successfully:', response);
        alert('Trade successfully submitted!');
        // Reset  page elements
        this.selectedStock = null;
        this.buyAmount = 0;
      },
      (error) => {
        console.error('Error adding trade:', error);
        alert('Failed to submit trade.');
      }
    );
      console.log('Purchasing', this.buyAmount, 'of', stock.symbol);
   
  }

  
}
