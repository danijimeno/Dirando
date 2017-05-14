import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { Inject, Injectable } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html'
})
export class RegistroComponent{

  edit = {
        name: "",
        fullName: "" ,
        email: "",
        phone: "",
        password: "",
        address: ""
    };
    private body : Object;
    private domain;
  constructor( @Inject(DOCUMENT) private document: any, private router: Router, private http: Http) { 
        this.domain=this.document.location.hostname;

  }

  crear() {
    this.body = {
        name: this.edit.name,
        fullName: this.edit.fullName,
        email:this.edit.email,
        phone: this.edit.phone,
        password: this.edit.password,
        address: this.edit.address
    };
    let url = "https://"+this.domain+":8443/rest/user";
    this.http.post(url,this.body).subscribe(
      response => console.log(response),
      error => console.error(error)
    );
    this.router.navigate(['/home']);
  }

}
