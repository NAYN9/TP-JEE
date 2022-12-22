import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBillComponent } from './add-bill/add-bill.component';
import { BillDetailsComponent } from './bill-details/bill-details.component';
import { BillsComponent } from './bills/bills.component';
import { CustomersComponent } from './customers/customers.component';
import { AuthGuard } from './guard/security.guard'; 
import { HomeComponent } from './home/home.component';
import { ProductsComponent } from './products/products.component';

const routes: Routes = [
  {
    path:"", component : HomeComponent
  },
  {path:"products",component:ProductsComponent, 
  canActivate:[AuthGuard],data:{roles:['USER']}},
{path:"customer",component:CustomersComponent, 
canActivate:[AuthGuard],data:{roles:['USER','ADMIN']}},
{path:"bills/:customerID",component:BillsComponent, 
canActivate:[AuthGuard],data:{roles:['USER']}},
{path:"bill-details/:orderID",component:BillDetailsComponent, 
canActivate:[AuthGuard],data:{roles:['USER']}},
{path:"add-bill",component:AddBillComponent, 
canActivate:[AuthGuard],data:{roles:['USER','ADMIN']}},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
