import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { RodapeAcoesComponent } from '../../components/rodape-acoes/rodape-acoes.component';

@Component({
  selector: 'app-transaction-details',
  imports: [HeaderComponent, RodapeAcoesComponent],
  templateUrl: './transaction-details.component.html',
  styleUrl: './transaction-details.component.scss'
})
export class TransactionDetailsComponent {

}
