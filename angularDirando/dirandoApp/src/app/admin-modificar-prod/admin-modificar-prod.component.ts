import { Component, OnInit } from '@angular/core';
import  {  ActivatedRoute, Router  }  from  '@angular/router';
import { LoginService } from '../login/login.service';
import { Http, Headers, RequestOptions } from '@angular/http';

@Component({
  selector: 'app-admin-modificar-prod',
  templateUrl: './admin-modificar-prod.component.html'
})


export class AdminModificarProdComponent implements OnInit {

  private nuevo = {
      "nombre": "",
      "desProducto": "",
      "precio": 0,
      "theBest": 0,
      "mustImprove": 0,
      "bad": 0,
      "image": "",
      "stock": 0,
      "categoria": ""
    }
//private nuevo: Object[] = [];
private categorias: Object[] = [];

  constructor(private http: Http, private activatedRoute:  ActivatedRoute, private loginService: LoginService, private router: Router) { 
    if (this.loginService.isAdmin == false) {
      this.router.navigate(['/home']);
    }
    
  }

  ngOnInit() {
    this.cargarProducto();
    this.loadCategories();
  }

  modificarProducto() {
    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    let  url  =  "https://localhost:8443/rest/admin/products/" + this.activatedRoute.snapshot.params['id'];
    this.http.put(url, this.nuevo ,options).subscribe(
      response  =>  {
        let data = response.json();
        console.log(data);
        alert('Producto Modificado');
        this.router.navigate(['/adminDetalleProducto',this.activatedRoute.snapshot.params['id']]);
      },
      error  =>  console.error(error)
    );
  }

  cargarProducto() {
    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    let  url  =  "https://localhost:8443/rest/productDetail/" + this.activatedRoute.snapshot.params['id'];
    this.http.get(url, options).subscribe(
      response  =>  {
        let data = response.json();
        console.log(data);
        /*this.nuevo.push({
          "image": data.image, "id": data.id, "nombre": data.nombre, "precio": data.precio,
          "theBest": data.theBest, "mustImprove": data.mustImprove, "bad": data.bad, "desProducto": data.desProducto,
          "stock": data.stock, "categoria": data.categoria
        });*/
        this.nuevo.nombre = data.nombre;
        this.nuevo.image = data.image;
        this.nuevo.desProducto = data.desProducto;
        this.nuevo.categoria = data.categoria;
        this.nuevo.precio =data.precio;
        this.nuevo.stock = data.stock;
        this.nuevo.theBest = data.theBest;
        this.nuevo.mustImprove = data.mustImprove;
        this.nuevo.bad = data.bad;
    
      },
      error  =>  console.error(error)
    );
  }

  loadCategories() {
    let url = "https://localhost:8443/rest/admin/categories/"
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

  cancelar() {
    this.router.navigate(['/adminDetalleProducto',this.activatedRoute.snapshot.params['id']]);
  }

}
