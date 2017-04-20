import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-indexbody',
  templateUrl: './indexbody.component.html',
  styleUrls: ['./indexbody.component.css']
})

export class IndexbodyComponent{
  private imagen: Object[]= [];

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
          console.log(data);
          for (var i = 0; i < data.length; i++) {
            this.imagen.push({"name":data[i].nomPublicidad,"image":data[i].imagen}); 
          }
        console.log(this.imagen);
        },
        error => console.error(error)
      );
   }
}
