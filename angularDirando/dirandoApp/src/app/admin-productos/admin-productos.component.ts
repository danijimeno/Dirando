import { Component, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import {Router} from '@angular/router';
import { LoginService } from '../login/login.service';
import { Inject, Injectable } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-admin-productos',
  templateUrl: './admin-productos.component.html'
})
export class AdminProductosComponent implements OnInit {

   private productos: Object[] = [];
   private page: number = 0;
   private size: number = 10;
   private domain;
  constructor( @Inject(DOCUMENT) private document: any, private http: Http, private router: Router, private loginService: LoginService) { 
    this.domain=this.document.location.hostname;
    if (this.loginService.isAdmin==false){
      this.router.navigate(['/home']);
    }
    
  }

  ngOnInit() {
    this.cargarProductos("ini");
  }

  cargarProductos(event: any){
    console.log(event);
    if(event=="sig"){
      this.page++;
      this.productos.length=0;
    }else if(event=="ant"){
      if (this.page>0){
        this.page--;
      }
      this.productos.length=0;
    }
    const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    let url = "https://"+this.domain+":8443/rest/admin/products/?page="+ this.page +"&size="+ this.size;
      this.http.get(url,options).subscribe(
        response => {
        
        let data = response.json();
        
        for (var i=0; i< data.content.length; i++){
          this.productos.push(data.content[i]);
        }
        
  },
        error => console.error(error)
      );
  }

}
