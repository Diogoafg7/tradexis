import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { TransactionsComponent } from './pages/transactions/transactions.component';
import { TransactionHistoryComponent } from './pages/transaction-history/transaction-history.component';
import { TransactionDetailsComponent } from './pages/transaction-details/transaction-details.component';
import { LoginComponent } from './authentication/login/login.component';

export const routes: Routes = [

    {path:'dashboard',component:DashboardComponent},
    {path:'profile',component:ProfileComponent},
    {path:'transactions', component:TransactionsComponent},
    {path: 'transaction-history',component:TransactionHistoryComponent},
    {path:'transaction-details/:id', component:TransactionDetailsComponent},
    {path:'',component:LoginComponent},
    {path:'login',component:LoginComponent},
    



      // default routes
    //   {
    //     path: '', redirectTo:'/login' , pathMatch:'full'
    // },
    // {
    //     path: '**', component: NotFoundComponent
    // }
];
