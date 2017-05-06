import { Component, OnInit } from '@angular/core';
import { LoginService } from "../login/login.service";

@Component({
  selector: 'app-administracion',
  templateUrl: './administracion.component.html',
  styleUrls: ['./administracion.component.css']
})
export class AdministracionComponent implements OnInit {

  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

  logOut() {
    this.loginService.logOut().subscribe(
      response => { },
      error => console.log('Error when trying to exit' + error)
    );
  }

}
