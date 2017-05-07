import { Component, OnInit } from '@angular/core';
import  {  ActivatedRoute, Router  }  from  '@angular/router';
import { LoginService } from '../login/login.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Component({
  selector: 'app-admin-añadir-producto',
  templateUrl: './admin-añadir-producto.component.html'
})
export class AdminAñadirProductoComponent {

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

  constructor(private http: Http, private activatedRoute:  ActivatedRoute, private loginService: LoginService, private router: Router) { 
    if (this.loginService.isAdmin == false) {
      this.router.navigate(['/home']);
    }
  }

  anadirProducto() {
    console.log(this.nuevo);
    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    let  url  =  "https://localhost:8443/rest/admin/products";
    this.http.post(url, this.nuevo ,options).subscribe(
      response  =>  {
        let data = response.json();
        console.log(data);
      },
      error  =>  console.error(error)
    );
  }

}
