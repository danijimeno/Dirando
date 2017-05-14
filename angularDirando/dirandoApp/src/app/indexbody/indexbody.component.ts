import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

import { HeaderComponent } from './../header/header.component';
import {Product} from '../product.model';
import {ShopService} from '../shop/shop.service';
import { Inject, Injectable } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-indexbody',
  templateUrl: './indexbody.component.html'
})

export class IndexbodyComponent implements OnInit {

  private imagen: Object[] = [];
  private items: Object[] = [];
  private product:Product;
  private domain;
  private URL: string;
  private URLimages: string
  constructor(private http: Http,private shopservice:ShopService, @Inject(DOCUMENT) private document: any) {
    this.domain = this.document.location.hostname;
    this.URL="https://"+this.domain+":8443/rest/";
    this.URLimages="https://"+this.domain+":8443/";
  }

  ngOnInit() {
    this.loadCarousel();
    this.loadItemsIndex("0", "3");
  }

  loadCarousel() {
    let url = this.URL + "carrusel";
    this.http.get(url).subscribe(
      response => {
        let data = response.json();
        for (var i = 0; i < data.length; i++) {
          this.imagen.push({ "name": data[i].nomPublicidad, "image": data[i].imagen });
        }
      },
      error => console.error(error)
    );
  }

  loadItemsIndex(page: string, size: string) {
    let url = this.URL + "indexItems?page=" + page + "&size=" + size;
    this.http.get(url).subscribe(
      response => {
        let data = response.json();
        let data2 = data.content;
        for (var i = 0; i < data2.length; i++) {
          this.items.push({ "imagen": this.URLimages+data2[i].image, "id": data2[i].id, "precio": data2[i].precio, "nombre": data2[i].nombre, "descripcion": data2[i].desProducto, "stock": data2[i].stock });
        }
      },
      error => console.error(error)
    );
  }

  addCart(product) {
    this.shopservice.addProductCart(product);
  }

  setStyles(valor: string){
    let styles= {'width': valor+"%" };
    return styles;
  } 
  
}
