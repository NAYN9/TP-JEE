import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-bill',
  templateUrl: './add-bill.component.html',
  styleUrls: ['./add-bill.component.css']
})
export class AddBillComponent implements OnInit {

  products:any;
  customers:any;
  pids:number[]=[];

  billFormGroup!: FormGroup;

  constructor(private http:HttpClient, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.pids=[]
    // GET PRODUCTS
    this.http.get("http://localhost:8888/INVENTORY-SERVICE/products").subscribe({
     next: (data)=>{
       this.products=data;
     },
     error: (err)=>{}
   });
   //END GET PRODUCTS

   this.http.get("http://localhost:8888/CUSTOMER-SERVICE/customers").subscribe({
     next: (data)=>{
       this.customers=data;
     },
     error: (err)=>{}
   });

   // Form
   this.billFormGroup=this.fb.group({
     customerID  : this.fb.control(""),
     productIDs  : this.fb.control("")
   });

 }



 test(obj:any,product:any){
   if ( obj.target.checked ) {
     if(!this.pids.includes(product.id))
     this.pids.push(product.id)
     console.log(this.pids)
}
 }


 save() {
  console.log(Date(),this.billFormGroup.value.customerID,this.pids)
  this.http.post<any>("http://localhost:8888/BILLING-SERVICE/addbill",{
    billingDate: "2022-12-18T11:21:42.404+00:00",
    customerID: this.billFormGroup.value.customerID,
    productIDs: this.pids
  }).subscribe({
    next:()=>{
      alert('Bill Added');
      this.ngOnInit();
    }
  })
 }

}
