import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import {Observable} from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class CarritoService {

  private cartSize :string;
  private cartSize$: Subject<string> = new Subject<string>();

  constructor(private http: Http) {}

  loadCartSize(){
    let url = "https://localhost:8443/rest/cartSize";
      this.http.get(url).subscribe(
        response => {
          let data = response.text();
          this.cartSize=data;
          console.log("El tamaño del carrito en el server (carritoService)",data);
          //Notifico que ha habido un nuevo evento
          this.cartSize$.next(this.cartSize);
        },
        error => console.error(error)
      );
  }

  addCart(idItem: string, nombreItem: string, precioItem: string) {
    let url = "https://localhost:8443/rest/cart";
    let data = {
      "id": idItem,
      "nombre": nombreItem,
      "precio": precioItem
    };
    this.http.put(url, data).subscribe(
      response => {
        console.log("Datos del item añadido al carro",response);
        this.loadCartSize();
      },
      error => console.error(error)
    );
  }
  
  /*Este método me permite suscribirme a los cambios de la variable*/
  getCartSize$(): Observable<string> {
        return this.cartSize$.asObservable();
  }
}
