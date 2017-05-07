import { Routes, RouterModule } from '@angular/router';

import { IndexbodyComponent } from './indexbody/indexbody.component';
import { RegistroComponent } from './registro/registro.component';
import {InfoUserComponent} from './usuario/user.component';
import { ListadoProductosComponent } from './listado-productos/listado-productos.component';
import { DetalleProductoComponent } from './detalle-producto/detalle-producto.component';
import { ModificarCuentaComponent } from './modificar-cuenta/modificar-cuenta.component';
import {ShopComponent} from './shop/shop.component';

const appRoutes = [
  { path: 'registro', component: RegistroComponent },
  { path: 'home', component: IndexbodyComponent },
  { path: 'user', component:InfoUserComponent},
  { path: 'listadoProductos/:producto', component: ListadoProductosComponent },
  { path: 'detalleProducto/:id', component: DetalleProductoComponent },
  { path: 'cuenta', component: ModificarCuentaComponent },
  {path: 'Cart', component: ShopComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }

]

export const routing = RouterModule.forRoot(appRoutes);