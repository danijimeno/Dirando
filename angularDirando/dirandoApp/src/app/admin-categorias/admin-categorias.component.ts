import { Component, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { Inject, Injectable } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-admin-categorias',
  templateUrl: './admin-categorias.component.html'
})
export class AdminCategoriasComponent implements OnInit  {

  private categorias: Object[] = [];

  private URL: string;

  private nuevaCat = {
    "nombre": ""
  }

  constructor( @Inject(DOCUMENT) private document: any, private  http:  Http,  private router: Router, private loginService: LoginService) { 
    this.URL = "https://"+this.document.location.hostname+":8443/rest/admin/categories/";
    if (this.loginService.isAdmin==false){
      this.router.navigate(['/home']);
    }
  }

  ngOnInit() {
    this.loadCategories();
  }

   loadCategories() {
    let url = this.URL;
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

  eliminarCategoria(catId: string){
    let url = this.URL + catId;
    const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    this.http.delete(url,options).subscribe(
      response => {
        this.categorias.length=0;
        this.loadCategories();
      },
      error => console.error(error)
    );  
  }

  anadirCategoria(nueva: string){
    let url = this.URL;
    this.nuevaCat = {
      "nombre": nueva
    }
    const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    this.http.post(url,this.nuevaCat,options).subscribe(
      response => {
        this.categorias.length=0;
        this.loadCategories();
      },
      error => console.error(error)
    );  
  }
}