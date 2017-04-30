import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Subject } from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class LogService {

  /*Si vale TRUE, el usuario está logueado, en el caso contrario
  el usuario no está logueado */
  private logCode: boolean;
  private logCode$: Subject<boolean> = new Subject<boolean>();
  
  constructor(private http: Http) { }


  isLogged(){
    let url = "https://localhost:8443/rest/log";
      this.http.get(url).subscribe(
        response => {
          let data = response.text();
          if (data == "Logged In!"){
            this.logCode=true;
            this.logCode$.next(this.logCode);
          }else{
            this.logCode=false;
            this.logCode$.next(this.logCode);
          }
          console.log(this.logCode);
        },
        error => console.error(error)
      );
  }
  /*Este método me permite suscribirme a los cambios de la variable logCode*/
  getLogCode$(): Observable<boolean> {
        return this.logCode$.asObservable();
  }
}
