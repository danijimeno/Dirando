import {Component} from '@angular/core';
import {LoginService} from '../login/login.service';
import  {  Router  }  from  '@angular/router';


@Component({
    selector:'app-infoUser',
    templateUrl:'./user.component.html'
})

export class InfoUserComponent{

    constructor(private loginService:LoginService, private router: Router){
        if (!this.loginService.isLogged){
            this.router.navigate(['/home']);
        }
    }
}