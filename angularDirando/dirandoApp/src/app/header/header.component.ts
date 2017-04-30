import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import {Router} from '@angular/router';
import { CommonModule } from '@angular/common';  
import { CarritoService } from './../carrito.service';
import { LoginService } from '../login/login.service';
import {Observable} from 'rxjs/Observable';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'

  
})
export class HeaderComponent{
  
  private cartSize: string;
    cartSize$: Observable<string>;
  private logCode: boolean;
    logCode$: Observable<boolean>;

  constructor(private http: Http,private loginService:LoginService, private carritoService: CarritoService) {
    /*Me suscribo a los cambios*/
      this.cartSize$ = this.carritoService.getCartSize$();
      this.cartSize$.subscribe(message => this.cartSize=message);
    /*Me suscribo a los cambios del tamaño de logCode, que me dirá si el usuario está logueado*/
    this.carritoService.loadCartSize();
   }


logIn(event:any,user:string,pass:string){
        event.preventDefault();
        this.loginService.loginIn(user,pass).subscribe(
            u => console.log(u),
            error => alert('Invalid user or password')
        );
    }

    logOut(){
      this.loginService.logOut().subscribe(
        response => {},
        error => console.log('Error when trying to exit' + error)
      );
    }
}
