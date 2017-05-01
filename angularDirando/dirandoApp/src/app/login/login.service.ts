import { Injectable, OnInit } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/Rx';

const URL = 'https://localhost:8443/rest';

export interface User {
    id?: number;
    name: string;
    roles: string[];
    full_name: string;
    email: string;
    imgRuta: string;
    address: string;
    phone: string;
}

@Injectable()
export class LoginService {

    isLogged = false;
    isAdmin = false;
    user: User;

    constructor(private http: Http) {
        this.reqIsLogged();
    }

    reqIsLogged() {

        const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });

        const options = new RequestOptions({ withCredentials: true, headers });

        this.http.get(URL + '/login', options).subscribe(
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

    }

    loginIn(user: string, pass: string) {

        const userPass = user + ':' + pass;

        const headers = new Headers({
            'Authorization': 'Basic ' + utf8_to_b64(userPass),
            'X-Requested-With': 'XMLHttpRequest'
        });

        const options = new RequestOptions({ withCredentials: true, headers });

        return this.http.get(URL + '/login', options).map(
            response => {
                this.processLogInResponse(response);
                return this.user;
            }
        );
    }


    logOut() {

        return this.http.get(URL + '/logout', { withCredentials: true }).map(
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