import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';


@Component({
  selector: 'app-listado-productos',
  templateUrl: './listado-productos.component.html'
})
export class ListadoProductosComponent{

  private items: Object[] = [];
  private page: number;
  private size: number;

  constructor(private http: Http) {
    this.page=0; this.size=6;
    this.loadItems();
  }

  loadItems() {
    let url = "https://localhost:8443/rest/items/xco?page=" + this.page + "&size=" + this.size;
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
    this.page=this.page+1;
  }

}
