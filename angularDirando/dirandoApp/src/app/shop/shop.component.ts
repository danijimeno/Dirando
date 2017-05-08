import {Component,OnInit} from '@angular/core';
import {ShopService} from './shop.service';
import { Router } from '@angular/router';
import {LoginService} from '../login/login.service';

import {Product} from '../product.model';

@Component({
    selector: 'shop-app',
    templateUrl: './shop.component.html'
})

export class ShopComponent implements OnInit{
    listProduct:Product[];
    constructor(private shopservice:ShopService,private loginservice:LoginService){
        
        
    }

    ngOnInit(){
        this.loadCart();
    }

    loadCart(){
        this.shopservice.cartProductList().subscribe(
           products => this.listProduct = products,
            error => console.log(error)
        );
    }

    clearCart(){
        this.shopservice.delProductCart().subscribe(
            response=> {this.loadCart();}
        );
        
    }

    payCart(){
        this.shopservice.payCart().subscribe(
            response=> {this.loadCart();}
        );
        
    }
    
}