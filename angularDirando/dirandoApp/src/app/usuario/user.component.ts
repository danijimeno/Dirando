import {Component} from '@angular/core';
import {Http} from '@angular/http';
import {LoginService} from '../login/login.service';


@Component({
    selector:'app-infoUser',
    templateUrl:'./user.component.html'
})

export class InfoUserComponent{

    constructor(private http:Http,private loginService:LoginService){}

    infoReq(){
        
    }
}