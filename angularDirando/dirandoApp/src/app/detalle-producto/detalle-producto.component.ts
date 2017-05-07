import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';
import {ShopService} from '../shop/shop.service';

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

  private productos: Object[] = [];
  private comentarios: Object[] = [];

  //Datos locales de comentarios para cargar en el template
    private comentario = {
      text: "",
      rating: ""
    }
    private valoracion: string = "3";
  
  constructor(private http: Http, private activatedRoute: ActivatedRoute, private loginService: LoginService, private shopservice: ShopService) {
    this.loadProduct(this.activatedRoute.snapshot.params['id']);  
  }

  addCart(product) {
    this.shopservice.addProductCart(product);
  }

  addComment(){
    let url = "https://localhost:8443/rest/commentary2";
    let data = {
        "name": this.loginService.user.name,
        "idProduct": this.activatedRoute.snapshot.params['id'],
        "comment": this.comentario.text,
        "rating": this.valoracion
    };
      this.http.put(url,data).subscribe(
        response => {
            this.comentarios.push({ 
              "user": data.name,
              "content": data.comment
            });
          this.loadProduct(this.activatedRoute.snapshot.params['id']);
        },
        error => console.error(error)
      );
  }

  loadProduct(id: number){
    this.productos.length=0;
    this.comentarios.length=0;
    let url = "https://localhost:8443/rest/productDetail/"+id;
      this.http.get(url).subscribe(
        response => {
          let data = response.json();

          let ra : Rating = this.calcula(data.theBest, data.mustImprove, data.bad);
          
          this.productos.push({ 
            "imagen": data.image,"id": data.id,"nombre": data.nombre,"precio": data.precio,
            "best": ra.best,"mustImprove": ra.improve,"bad": ra.bad,"descripcion": data.desProducto,
            "stock": data.stock });
            console.log(data);
          for(let i=0; i<data.comments.length; i++){
            this.comentarios.push({ 
              "user": data.comments[i].user,
              "content": data.comments[i].content
            });
          }
           console.log("comentarios: ",this.comentarios);
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

  setBest(event){
    this.valoracion="3";
  }
  setImprove(event){
    this.valoracion="2";
  }
  setBad(event){
    this.valoracion="1";
  }

}