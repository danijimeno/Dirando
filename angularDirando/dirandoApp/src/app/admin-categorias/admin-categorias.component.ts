import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-admin-categorias',
  templateUrl: './admin-categorias.component.html'
})
export class AdminCategoriasComponent {

  private categorias: Object[] = [];

  private URL: string = "https://localhost:8443/rest/admin/categories/";

  private nuevaCat = {
    "nombre": ""
  }

  constructor(private  http:  Http) { 
    this.loadCategories();
  }

   loadCategories() {
    let url = this.URL;
    this.http.get(url).subscribe(
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
    this.http.delete(url).subscribe(
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
    this.http.post(url,this.nuevaCat).subscribe(
      response => {
        this.categorias.length=0;
        this.loadCategories();
      },
      error => console.error(error)
    );  
  }
}