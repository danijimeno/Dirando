import { Component } from '@angular/core';
import {LoginService} from '../login/login.service';
import { Http } from '@angular/http';

@Component({
  selector: 'app-modificar-cuenta',
  templateUrl: './modificar-cuenta.component.html'
})
export class ModificarCuentaComponent{

  private body : Object;

  edit = {
        fullName: "" ,
        email: "",
        phone: "",
        password: "",
        address: ""
    };

  constructor(private http: Http, private loginService: LoginService) {}
  
  modifica() {
    this.body = {
        name: this.loginService.user['name'],
        fullName: this.edit.fullName,
        email:this.edit.email,
        phone: this.edit.phone,
        password: this.edit.password,
        address: this.edit.address
    };
    let url = "https://localhost:8443/rest/account2";
    this.http.put(url,this.body).subscribe(
      response => {console.log(response),
                  this.loginService.logOut()
                  },
      error => console.error(error)
    );
  }

}
