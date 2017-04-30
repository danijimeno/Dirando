import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { CommonModule } from '@angular/common';  

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { IndexbodyComponent } from './indexbody/indexbody.component';
import { routing } from './app.routing';
import { RegistroComponent } from './registro/registro.component';
import {InfoUserComponent} from './usuario/user.component';
import { CarritoService } from './carrito.service';
import { LoginService } from './login/login.service';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    IndexbodyComponent,
    RegistroComponent,
    InfoUserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CarouselModule.forRoot(),
    routing,
    CommonModule
  ],
  providers: [CarritoService,LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
