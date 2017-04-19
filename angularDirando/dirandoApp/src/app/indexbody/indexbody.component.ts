import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';


@Component({
  selector: 'app-indexbody',
  templateUrl: './indexbody.component.html',
  styleUrls: ['./indexbody.component.css']
})
export class IndexbodyComponent{
  
  private imagen: string[]= [];

  constructor(private http: Http) {
    console.log("Llega");
    this.loadCarousel();
   }
  loadCarousel() {
    console.log("Llega2");
      let url = "https://localhost:8443/rest/carrusel";
      this.http.get(url).subscribe(
        response => {
          let data = response.json();
          for (var i = 0; i < data.length; i++) {
            let img = data[i].imagen;
            let nom = data[i].nomPublicidad;
            //this.imagen.push({"src":"img","alt":"nom"});
          }
      console.log(this.imagen);
        },
        error => console.error(error)
      );
   }


}
