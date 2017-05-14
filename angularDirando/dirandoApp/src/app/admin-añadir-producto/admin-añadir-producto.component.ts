import { Component, OnInit } from '@angular/core';
import  {  ActivatedRoute, Router  }  from  '@angular/router';
import { LoginService } from '../login/login.service';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Inject, Injectable } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-admin-añadir-producto',
  templateUrl: './admin-añadir-producto.component.html'
})
export class AdminAñadirProductoComponent implements OnInit {

  private nuevo = {
      "nombre": "",
      "desProducto": "",
      "precio": 0,
      "theBest": 0,
      "mustImprove": 0,
      "bad": 0,
      "image": "img/ejemplo2.jpg",
      "stock": 0,
      "categoria": ""
    }
  private domain;
  private categorias: Object[] = [];

  constructor( @Inject(DOCUMENT) private document: any, private http: Http, private activatedRoute:  ActivatedRoute, private loginService: LoginService, private router: Router) { 
    this.domain = this.document.location.hostname;
    if (this.loginService.isAdmin == false) {
      this.router.navigate(['/home']);
    }
  }

  ngOnInit() {
    this.loadCategories();
  }

  anadirProducto() {
    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    let  url  =  "https://"+this.domain+":8443/rest/admin/products";
    this.http.post(url, this.nuevo ,options).subscribe(
      response  =>  {
        let data = response.json();
        console.log(data);
        alert('Producto Añadido');
        this.router.navigate(['/adminProductos']);
      },
      error  =>  console.error(error)
    );
  }

loadCategories() {
    let url = "https://"+this.domain+":8443/rest/admin/categories/"
    const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    this.http.get(url,options).subscribe(
      response => {
        let data = response.json();
        for (var i=0; i< data.length; i++){
          this.categorias.push(data[i]);
        }
      },
      error => console.error(error)
    );
  }

}
