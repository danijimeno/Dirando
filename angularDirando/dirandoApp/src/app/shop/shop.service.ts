import {Injectable,Output,EventEmitter} from '@angular/core';
import {Http, RequestOptions,Headers} from '@angular/http';
import 'rxjs/Rx';
import {Product} from '../product.model';
import { Inject } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';


@Injectable()
export class ShopService{
    private URL : string
    private num:number;
    private product:Product;
    private products:Product[]=[];
    private domain;
    constructor( @Inject(DOCUMENT) private document: any, private http:Http){
        this.URL = "https://"+this.document.location.hostname+":8443/rest"
    }

    addProductCart(product){
        const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers});
        console.log(product.name);
        this.http.put(this.URL+'/addCart/'+product.id,product,options).subscribe(
             response => {
            console.log("Datos del item añadido al carro",response.json()),
            this.cartSize()}
        );
        
    }
    delProductCart(){
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers});        
        return this.http.delete(this.URL+'/clearCart',options).map(
            response => {console.log('Delete cart correctly'),
            this.cartSize()}
        );
    }

    cartProductList(){
        const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers});
       return this.http.get(this.URL+'/cart',options).map(
          response => {let products = response.json() as Product[]
                        console.log(products);
                        this.cartSize()
                        return products
          }

        );
        
    }

    cartSize(){
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
    });
        const options = new RequestOptions({ withCredentials: true, headers});

       this.http.get(this.URL+'/cartSize',options).subscribe(
            response => this.num = response.json(),
                    
            error => console.info(error)
        );
        return this.num;
    }

    

    payCart(){
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
    });
        const options = new RequestOptions({ withCredentials: true, headers});

       return this.http.post(this.URL+'/pay',this.products,options).map(
            response => {console.log(response)
            this.cartSize()},
                    
            error => console.info(error)
        );
    }
}