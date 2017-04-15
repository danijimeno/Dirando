import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {HTTP_PROVIDERS, Http} from 'angular2/http';
import {HeaderComponent} from './header.component';
import {FooterComponent} from './footer.component';
import {IndexBodyComponent} from './indexbody.component';

import {Alert} from 'ng2-bootstrap/ng2-bootstrap';

@Component({
  selector: 'app',
  template:`
        <header-app></header-app>
        <router-outlet></router-outlet>
        <footer-app></footer-app>
`,
  providers:  [HTTP_PROVIDERS],
  directives: [HeaderComponent,FooterComponent,ROUTER_DIRECTIVES, Alert]
})
@RouteConfig([
    {path: '/', name: 'Cuerpo', component: IndexBodyComponent, useAsDefault: true}
])
export class AppComponent {	
}
