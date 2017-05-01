import { Component, OnInit } from '@angular/core';
import {LoginService} from '../login/login.service';

@Component({
  selector: 'app-modificar-cuenta',
  templateUrl: './modificar-cuenta.component.html'
})
export class ModificarCuentaComponent{

  constructor(private loginService: LoginService) { }


}
