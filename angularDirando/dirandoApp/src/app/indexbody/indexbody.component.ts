import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

import { HeaderComponent } from './../header/header.component';

@Component({
  selector: 'app-indexbody',
  templateUrl: './indexbody.component.html'
})

export class IndexbodyComponent {

  private imagen: Object[] = [];
  private items: Object[] = [];


  constructor(private http: Http) {
    this.loadCarousel();
    this.loadItemsIndex("0", "3");
  }

  loadCarousel() {
    let url = "https://localhost:8443/rest/carrusel";
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
    let url = "https://localhost:8443/rest/indexItems?page=" + page + "&size=" + size;
    this.http.get(url).subscribe(
      response => {
        let data = response.json();
        let data2 = data.content;
        for (var i = 0; i < data2.length; i++) {
          this.items.push({ "imagen": data2[i].image, "id": data2[i].id, "precio": data2[i].precio, "nombre": data2[i].nombre, "descripcion": data2[i].desProducto, "stock": data2[i].stock });
        }
      },
      error => console.error(error)
    );
  }

  addCart(idItem: string, nombreItem: string, precioItem: string) {
    console.log(idItem, nombreItem, precioItem);
    let url = "https://localhost:8443/rest/cart";
    let data = {
      "id": idItem,
      "nombre": nombreItem,
      "precio": precioItem
    };
    this.http.put(url, data).subscribe(
      response => {
        console.log(response);
      },
      error => console.error(error)
    );
  }
}
