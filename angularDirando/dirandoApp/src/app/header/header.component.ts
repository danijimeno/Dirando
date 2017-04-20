import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent{
  private cartSize: string;
  constructor(private http: Http) {
    this.loadCartSize();
   }

  loadCartSize(){
    let url = "https://localhost:8443/rest/cartSize";
      this.http.get(url).subscribe(
        response => {
          let data = response.json();
          this.cartSize=data;
        },
        error => console.error(error)
      );
  }
}
