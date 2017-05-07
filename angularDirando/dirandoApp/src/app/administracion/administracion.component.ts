import { Component, OnInit } from '@angular/core';
import { LoginService } from "../login/login.service";
import {Router} from '@angular/router';

@Component({
  selector: 'app-administracion',
  templateUrl: './administracion.component.html',
  styleUrls: ['./administracion.component.css']
})
export class AdministracionComponent{

  constructor(private loginService: LoginService, private router: Router) { 
    if (this.loginService.isAdmin==false){
      this.router.navigate(['/home']);
    }
  }

  logOut() {
    this.loginService.logOut().subscribe(
      response => { },
      error => console.log('Error when trying to exit' + error)
    );
  }

}
