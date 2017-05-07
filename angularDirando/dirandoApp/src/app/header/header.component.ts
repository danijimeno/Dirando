import { Component, OnInit ,Input} from '@angular/core';
import  {  Http  }  from  '@angular/http';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LoginService } from '../login/login.service';
import {ShopService} from '../shop/shop.service';
import { Observable } from 'rxjs/Observable';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'


})
export class HeaderComponent {

  private busquedaItem: string;
  private logCode: boolean;
  private num:number;

  constructor(private  http:  Http, private loginService: LoginService,private shopservice:ShopService)  {
  this.reloadCart()
}

  reloadCart(){
    return this.num = this.shopservice.cartSize(); 
  }

  setBusqueda( busqueda: string ){
    this.busquedaItem=busqueda;
  }

  logIn(event: any, user: string, pass: string) {
    event.preventDefault();
    this.loginService.loginIn(user, pass).subscribe(
      u => console.log(u),
      error => alert('Invalid user or password')
    );
  }

  logOut() {
    this.loginService.logOut().subscribe(
      response => {this.reloadCart() },
      error => console.log('Error when trying to exit' + error)
    );
  }
}
