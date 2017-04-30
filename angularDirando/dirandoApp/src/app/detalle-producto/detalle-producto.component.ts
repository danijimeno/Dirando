import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute } from '@angular/router';

interface Rating{
  best: string,
  improve: string,
  bad: string
}

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html'
})

export class DetalleProductoComponent{

  
  private logged: boolean = false;
  private productos: Object[] = [];
  
  constructor(private http: Http, activatedRoute: ActivatedRoute) {
    this.loadProduct(activatedRoute.snapshot.params['id']);  
  }

  loadProduct(id: number){
    let url = "https://localhost:8443/rest/productDetail/"+id;
      this.http.get(url).subscribe(
        response => {
          let data = response.json();
          let ra : Rating = this.calcula(data.theBest, data.mustImprove, data.bad);
          
          this.productos.push({ 
            "imagen": data.image,"id": data.id,"nombre": data.nombre,"precio": data.precio,
            "best": ra.best,"mustImprove": ra.improve,"bad": ra.bad,"descripcion": data.desProducto,
            "stock": data.stock });

          console.log("este es el producto pedido: ",this.productos);
        },
        error => console.error(error)
      );
  }

  //Calcula el porcentaje a partir del numero de votos 
  calcula(best: string, mustImprove: string, bad: string){
    let total: number=Number(best)+Number(mustImprove)+Number(bad);
    let be: number = Number(best) / total * 100;
    let im: number = Number(mustImprove) / total * 100;
    let ba: number = Number(bad) / total * 100;
    let rating : Rating = {best: be.toFixed(0),improve: im.toFixed(0),bad: ba.toFixed(0)};
    return rating;
  }

  setStyles(valor: string){
    let styles= {'width': valor+"%" };
    return styles;
  } 


}
