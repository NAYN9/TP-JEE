import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { throwError } from 'rxjs'; 

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
   
  products:any;  
  postId: any;
  BaseURL = 'http://localhost:8888/INVENTORY-SERVICE/';
  constructor(private http:HttpClient  ) {}

  ngOnInit(): void {
    this.http.get(this.BaseURL + 'products?projecton=fullProduct').subscribe({
      next: (data: object) => {
        this.products = data;
      }
    });
  }

  create(pn: String, pp: String, pq: String): void {
    this.http
      .post<any>(this.BaseURL+"addproduct", {
        name: pn,
        prix: pp,
        qte: pq,
      })
      .subscribe({
        next: () => {
          alert('Product Added');
          this.ngOnInit();
        }
      });
  }

  delete(id: any): void {
    this.http.delete(this.BaseURL+"deleteproduct/"+id).subscribe({
      next: (data: object) => {
        alert('Product Deleted');
        this.ngOnInit();
      }
    });
  }
}
