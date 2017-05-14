import { Component, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Router } from "@angular/router";
import { LoginService } from "../login/login.service";
import { Inject, Injectable } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';


@Component({
  selector: 'app-administracion',
  templateUrl: './administracion.component.html'
})
export class AdministracionComponent implements OnInit {
 

  private URL: string;

  private numPed;
  private numProd;
  private numUsers;
  private numCom;
  private numPunt;
  private numCat;

  constructor( @Inject(DOCUMENT) private document: any, private  http:  Http,private loginService: LoginService, private router: Router) { 
      this.URL = "https://"+this.document.location.hostname+":8443/rest/admin/index/";
      if (this.loginService.isAdmin==false){
        this.router.navigate(['/home']);
      }
  }

  ngOnInit() {
    this.loadIndex();
  }

  loadIndex() {
    let url = this.URL;
    const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    this.http.get(url,options).subscribe(
      response => {
        let data = response.json();
        
        this.numPed = data[0];
        this.numProd = data[1];
        this.numUsers = data[2];
        this.numCom = data[3];
        this.numPunt = data[4];
        this.numCat = data[5];

      },
      error => console.error(error)
    );
  }

}
