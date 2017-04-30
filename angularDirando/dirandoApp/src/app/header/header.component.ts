import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { CommonModule } from '@angular/common';  
import { CarritoService } from './../carrito.service';
import {Observable} from 'rxjs/Observable';
import { LogService } from './../log.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'

  
})
export class HeaderComponent{
  
  private cartSize: string;
    cartSize$: Observable<string>;
  private logCode: boolean;
    logCode$: Observable<boolean>;

  constructor(private http: Http, private carritoService: CarritoService,private logService: LogService) {
    this.logCode=false;
    /*Me suscribo a los cambios del tamaño de carrito*/
      this.cartSize$ = this.carritoService.getCartSize$();
      this.cartSize$.subscribe(message => this.cartSize=message);
    /*Me suscribo a los cambios del tamaño de logCode, que me dirá si el usuario está logueado*/
      this.logCode$ = this.logService.getLogCode$();
      this.logCode$.subscribe(message => this.logCode=message);
    this.carritoService.loadCartSize();
    this.logService.isLogged();
   }
}
