import { Component, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import {Router} from '@angular/router';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-admin-productos',
  templateUrl: './admin-productos.component.html'
})
export class AdminProductosComponent implements OnInit {

   private productos: Object[] = [];
   private page: number = 0;
   private size: number = 10;

  constructor(private http: Http, private router: Router, private loginService: LoginService) { 
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
    let url = "https://localhost:8443/rest/admin/products/?page="+ this.page +"&size="+ this.size;
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
