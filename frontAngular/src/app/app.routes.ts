import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { TransactionsComponent } from './pages/transactions/transactions.component';
import { TransactionHistoryComponent } from './pages/transaction-history/transaction-history.component';
import { TransactionDetailsComponent } from './pages/transaction-details/transaction-details.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';


export const routes: Routes = [

    {path:'dashboard',component:DashboardComponent},
    {path:'profile',component:ProfileComponent},
    {path:'transactions', component:TransactionsComponent},
    {path: 'transaction-history',component:TransactionHistoryComponent},
    {path:'transaction-details/:id', component:TransactionDetailsComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    

    



      // default routes
      {path:'',component:LoginComponent},
     {
         path: '**', component: LoginComponent
     }
];
