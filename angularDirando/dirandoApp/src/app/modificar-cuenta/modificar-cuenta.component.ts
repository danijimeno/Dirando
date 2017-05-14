import { Component } from '@angular/core';
import {LoginService} from '../login/login.service';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { Inject, Injectable } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-modificar-cuenta',
  templateUrl: './modificar-cuenta.component.html'
})
export class ModificarCuentaComponent{

  private body : Object;

  edit = {
        fullName: "" ,
        email: "",
        phone: "",
        password: "",
        address: ""
    };
    private domain;
  constructor( @Inject(DOCUMENT) private document: any, private http: Http, private router: Router, private loginService: LoginService) {
        this.domain=this.document.location.hostname;
  }
  
  modifica() {
    this.body = {
        name: this.loginService.user['name'],
        fullName: this.edit.fullName,
        email:this.edit.email,
        phone: this.edit.phone,
        password: this.edit.password,
        address: this.edit.address
    };
    let url = "https://"+this.domain+":8443/rest/account2";
    this.http.put(url,this.body).subscribe(
      response => {console.log(response),
                  this.loginService.logOut()
                  },
      error => console.error(error)
    );
    this.router.navigate(['/home']);
  }

}
