import { Component } from '@angular/core';
import { CarouselConfig } from 'ngx-bootstrap/carousel';
 
@Component({
  selector: 'demo-carousel-config',
  templateUrl: './carrusel.component.html',
  providers: [{provide: CarouselConfig, useValue: {interval: 2500, noPause: true}}]
})
export class CarruselComponent {
}
