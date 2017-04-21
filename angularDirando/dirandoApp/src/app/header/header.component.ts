import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { CommonModule } from '@angular/common';  

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent{
  private cartSize: string;
  private logCode: boolean;

  constructor(private http: Http) {
    this.logCode=false;
    this.loadCartSize();
    this.isLogged();
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

  isLogged(){
    let url = "https://localhost:8443/rest/log";
      this.http.get(url).subscribe(
        response => {
          let data = response.text();
          if (data == "Logged In!"){
            this.logCode=true;
          }else{
            this.logCode=false;
          }
          console.log(this.logCode);
        },
        error => console.error(error)
      );
  }
}
