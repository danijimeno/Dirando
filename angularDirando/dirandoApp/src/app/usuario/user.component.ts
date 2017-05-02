import {Component} from '@angular/core';
import {LoginService} from '../login/login.service';


@Component({
    selector:'app-infoUser',
    templateUrl:'./user.component.html'
})

export class InfoUserComponent{

    constructor(private loginService:LoginService){}
}