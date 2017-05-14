import { Injectable, OnInit } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/Rx';
import { Inject } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';



export interface User {
    id?: number;
    name: string;
    role: string[];
    full_name: string;
    email: string;
    imgRuta: string;
    address: string;
    phone: string;
}

@Injectable()
export class LoginService {
    private URL : string;
    isLogged = false;
    isAdmin = false;
    user: User;
    private domain;
    constructor( @Inject(DOCUMENT) private document: any, private http: Http) {
        this.domain = this.document.location.hostname;
        this.URL ="https://"+this.domain+":8443/rest";
        this.reqIsLogged();
    }

    reqIsLogged() {

        const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });

        const options = new RequestOptions({ withCredentials: true, headers });

        this.http.get(this.URL + '/login', options).subscribe(
            response => this.processLogInResponse(response),
            error => {
                if (error.status !== 401) {
                    console.error('Error when asking if logged: ' +
                        JSON.stringify(error));
                }
            }
        );
    }

    private processLogInResponse(response) {
        this.isLogged = true;
        console.log(response);
        this.user = response.json();

        for(let rol of this.user.role){
            if(rol == "ROLE_ADMIN"){
                this.isAdmin = true;
            }
        }
        console.log("Administrador " + this.isAdmin);

    }

    loginIn(user: string, pass: string) {

        const userPass = user + ':' + pass;

        const headers = new Headers({
            'Authorization': 'Basic ' + utf8_to_b64(userPass),
            'X-Requested-With': 'XMLHttpRequest'
        });

        const options = new RequestOptions({ withCredentials: true, headers });

        return this.http.get(this.URL + '/login', options).map(
            response => {
                this.processLogInResponse(response);
                return this.user;
            }
        );
    }


    logOut() {

        return this.http.get(this.URL + '/logout', { withCredentials: true }).map(
            response => {
                this.isLogged = false;
                this.isAdmin = false;
                return response;
            }
        );
    }
}

function utf8_to_b64(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
        return String.fromCharCode(<any>'0x' + p1);
    }));
}