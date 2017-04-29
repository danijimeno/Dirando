import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { CommonModule } from '@angular/common';  
import { CarritoService } from './../carrito.service';
import {Observable} from 'rxjs/Observable';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'

  
})
export class HeaderComponent{
  
  private cartSize: string;
  private logCode: boolean;
  cartSize$: Observable<string>;

  constructor(private http: Http, private carritoService: CarritoService) {
    this.logCode=false;
    /*Me suscribo a los cambios*/
      this.cartSize$ = this.carritoService.getCartSize$();
      this.cartSize$.subscribe(message => this.cartSize=message);
    //this.carritoService.loadCartSize(this.http);
    this.isLogged();
   }

  isLogged(){
    let url = "https://localhost:8443/rest/log";
      this.http.get(url).subscribe(
        response => {
          let data = response.text();
          if (data == "Logged In!"){
            this.logCode=true;
          }else{
            this.logCode=false;
          }
          console.log(this.logCode);
        },
        error => console.error(error)
      );
  }
}
