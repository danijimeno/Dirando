import { Routes, RouterModule } from '@angular/router';

import { IndexbodyComponent } from './indexbody/indexbody.component';
import { RegistroComponent } from './registro/registro.component';
import { ListadoProductosComponent } from './listado-productos/listado-productos.component';

const appRoutes = [
  { path: 'registro', component: RegistroComponent },
  { path: 'home', component: IndexbodyComponent },
  { path: 'listadoProductos', component: ListadoProductosComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);