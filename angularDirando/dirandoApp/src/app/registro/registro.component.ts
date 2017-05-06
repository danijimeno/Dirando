import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html'
})
export class RegistroComponent{

  edit = {
        name: "",
        fullName: "" ,
        email: "",
        phone: "",
        password: "",
        address: ""
    };
    private body : Object;

  constructor( private router: Router, private http: Http) { }

  crear() {
    this.body = {
        name: this.edit.name,
        fullName: this.edit.fullName,
        email:this.edit.email,
        phone: this.edit.phone,
        password: this.edit.password,
        address: this.edit.address
    };
    let url = "https://localhost:8443/rest/user";
    this.http.post(url,this.body).subscribe(
      response => console.log(response),
      error => console.error(error)
    );
    this.router.navigate(['/home']);
  }

}
