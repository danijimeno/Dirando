import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {HTTP_PROVIDERS, Http} from 'angular2/http';

@Component({
    selector: 'footer-app',
    templateUrl: 'app/footer.component.html',
    providers:  [HTTP_PROVIDERS],
    directives: [ROUTER_DIRECTIVES]  
})

export class FooterComponent{}