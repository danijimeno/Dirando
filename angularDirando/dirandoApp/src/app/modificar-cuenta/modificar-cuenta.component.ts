import { Component } from '@angular/core';
import {LoginService} from '../login/login.service';
import { Http } from '@angular/http';

export interface User {
    name: string;
    full_name: string;
    email: string;
    address: string;
    phone: string;
}

@Component({
  selector: 'app-modificar-cuenta',
  templateUrl: './modificar-cuenta.component.html'
})
export class ModificarCuentaComponent{
  edit: {
        name: "user",
        fullName:"nuevo nombre comleto",
        email:"nuevo correo",
        phone:"98789797",
        password:"1234",
        address: "Nueva Calle los rosales 10"
       };


  constructor(private http: Http, private loginService: LoginService) { 
  }
  
  //Método de prueba para nuevo mapping en backend (not work)
  modifica() {
    let url = "https://localhost:8443/rest/account2";
    this.http.put(url,this.edit).subscribe(
      response => {
        console.log(response);
        },
      error => console.error(error)
    );
  }

}
