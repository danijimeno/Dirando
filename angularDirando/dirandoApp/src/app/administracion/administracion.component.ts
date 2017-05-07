import { Component, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Router } from "@angular/router";
import { LoginService } from "../login/login.service";

@Component({
  selector: 'app-administracion',
  templateUrl: './administracion.component.html',
  styleUrls: ['./administracion.component.css']
})
export class AdministracionComponent{
 

  private URL: string = "https://localhost:8443/rest/admin/index/";

  private numPed;
  private numProd;
  private numUsers;
  private numCom;
  private numPunt;
  private numCat;

  constructor(private  http:  Http,private loginService: LoginService, private router: Router) { 
      if (this.loginService.isAdmin==false){
        this.router.navigate(['/home']);
      }
      this.loadIndex();
  }

  ngOnInit() {
  }

  loadIndex() {
    let url = this.URL;
    const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    this.http.get(url,options).subscribe(
      response => {
        let data = response.json();
        
        this.numPed = data[0];
        this.numProd = data[1];
        this.numUsers = data[2];
        this.numCom = data[3];
        this.numPunt = data[4];
        this.numCat = data[5];

      },
      error => console.error(error)
    );
  }

  logOut() {
    this.loginService.logOut().subscribe(
      response => { },
      error => console.log('Error when trying to exit' + error)
    );
  }

}
