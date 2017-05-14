import { Component, OnInit } from '@angular/core';
import  {  ActivatedRoute, Router  }  from  '@angular/router';
import { LoginService } from '../login/login.service';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Inject, Injectable } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-admin-detalle-producto',
  templateUrl: './admin-detalle-producto.component.html'
})
export class AdminDetalleProductoComponent implements OnInit  {

  private producto: Object[] = [];
  private domain;
  constructor( @Inject(DOCUMENT) private document: any, private http: Http, private activatedRoute:  ActivatedRoute, private loginService: LoginService, private router: Router) {
    this.domain=this.document.location.hostname;
    if (this.loginService.isAdmin == false) {
      this.router.navigate(['/home']);
    }
  }

    ngOnInit() {
      this.cargarProducto();
    }


  cargarProducto() {
    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    let  url  =  "https://"+this.domain+":8443/rest/productDetail/" + this.activatedRoute.snapshot.params['id'];
    this.http.get(url, options).subscribe(
      response  =>  {
        let data = response.json();
        console.log(data);
        this.producto.push({
          "imagen": data.image, "id": data.id, "nombre": data.nombre, "precio": data.precio,
          "best": data.theBest, "mustImprove": data.mustImprove, "bad": data.bad, "desProducto": data.desProducto,
          "stock": data.stock, "categoria": data.categoria
        });
      },
      error  =>  console.error(error)
    );
  }

  eliminarProducto(){
    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    let  url  =  "https://"+this.domain+":8443/rest/admin/products/" + this.activatedRoute.snapshot.params['id'];
    this.http.delete(url, options).subscribe(
      response  =>  {
        alert('El producto ha sido eliminado');
        this.router.navigate(['/adminProductos']);
      },
      error  =>  console.error(error)
    );
  }

  modificarProducto(){
    this.router.navigate(['/administracion/editarProducto',this.activatedRoute.snapshot.params['id']])
  }
}
