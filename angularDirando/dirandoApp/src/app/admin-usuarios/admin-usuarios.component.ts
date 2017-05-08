import { Component, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import {Router} from '@angular/router';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-admin-usuarios',
  templateUrl: './admin-usuarios.component.html'
})
export class AdminUsuariosComponent implements OnInit {

  private usuarios : Object [] = [];
  constructor(private http: Http, private router: Router, private loginService: LoginService) { 
    
  }

  ngOnInit() {
    this.cargarUsuarios();
  }

  cargarUsuarios(){
    const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    let url = "https://localhost:8443/rest/admin/users";
      this.http.get(url, options).subscribe(
        response => {
          let data = response.json();
          for (var i=0; i< data.length; i++){
            this.usuarios.push(data[i]);
          }
          console.log(this.usuarios);
        
  },
        error => console.error(error)
      );
  }

}
