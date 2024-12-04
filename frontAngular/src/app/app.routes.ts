import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { TransactionsComponent } from './pages/transactions/transactions.component';
import { TransactionHistoryComponent } from './pages/transaction-history/transaction-history.component';
import { TransactionDetailsComponent } from './pages/transaction-details/transaction-details.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { AuthGuard } from './auth.guard';
import { ProfileDetailsComponent } from './pages/profile-details/profile-details.component';

export const routes: Routes = [

    { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
    { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
    { path: 'profile-details/:id', component: ProfileDetailsComponent, canActivate: [AuthGuard]},
    { path: 'transactions', component: TransactionsComponent, canActivate: [AuthGuard] },
    { path: 'transaction-history', component: TransactionHistoryComponent, canActivate: [AuthGuard] },
    { path: 'transaction-details/:id', component: TransactionDetailsComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent},
    { path: 'register', component: RegisterComponent},
    
    
    // default routes
    {path:'',component:LoginComponent},
   {
       path: '**', component: LoginComponent
   }
];
