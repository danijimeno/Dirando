import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute } from '@angular/router';
import {ShopService} from '../shop/shop.service';


@Component({
  selector: 'app-listado-productos',
  templateUrl: './listado-productos.component.html'
})
export class ListadoProductosComponent{

  private items: Object[] = [];
  private categoria: string;
  private page: number;
  private size: number;

  constructor(private http: Http, activatedRoute: ActivatedRoute,private shopservice:ShopService) {
    this.page=0; this.size=6;
    this.loadItems(activatedRoute.snapshot.params['producto']);
  }

  addCart(id:number){
    this.shopservice.addProductCart(id);
  }


  loadItems(producto: string) {
    let url = "https://localhost:8443/rest/items/"+ producto +"?page=" + this.page + "&size=" + this.size;
    this.http.get(url).subscribe(
      response => {
        let data = response.json();
        let data2 = data.content;
        for (var i = 0; i < data2.length; i++) {
          if (i==0){
            this.categoria= data2[i].categoria;
          }
          this.items.push({ "categoria": data2[i].categoria, "imagen": data2[i].image, "id": data2[i].id, "precio": data2[i].precio, "nombre": data2[i].nombre, "descripcion": data2[i].desProducto, "stock": data2[i].stock });
        }
      },
      error => console.error(error)
    );
    this.page=this.page+1;
  }

  setStyles(valor: string){
    let styles= {'width': valor+"%" };
    return styles;
  } 

}
