import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit {
  orders:any;
  customerId!:number;
  products:any;
  constructor(private http:HttpClient,private router:Router,private route:ActivatedRoute) { 
    this.customerId=route.snapshot.params['customerID']
  }

  ngOnInit(): void {
      // this.http.get("http://localhost:8888/INVENTORY-SERVICE/products?projecton=fullProduct").subscribe({
      //   next: (data: object) => {
      //     this.products._embedded.products = data;
      //     console.log(data);
      //   }
      // });
    
    this.http.get("http://localhost:8888/BILLING-SERVICE/bills/search/byCustomerId?customerID="+this.customerId).subscribe({
      next:(data:object)=>{
        this.orders=data;
        console.log(data)
      },
      error:()=>{}
    })
  }
  
  getOrderDetails(o:any){
    this.router.navigateByUrl("/bill-details/"+o.id)
  }

  delete(id:any):void{
    this.http.delete("http://localhost:8888/BILLING-SERVICE/deletebill/"+id).subscribe({
      next:()=>{
        alert('Bill Deleted');
        this.ngOnInit();
      }
    })
  }

}
