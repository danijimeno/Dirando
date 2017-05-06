import { Routes, RouterModule } from '@angular/router';

import { IndexbodyComponent } from './indexbody/indexbody.component';
import { RegistroComponent } from './registro/registro.component';
import {InfoUserComponent} from './usuario/user.component';
import { ListadoProductosComponent } from './listado-productos/listado-productos.component';
import { DetalleProductoComponent } from './detalle-producto/detalle-producto.component';
import { ModificarCuentaComponent } from './modificar-cuenta/modificar-cuenta.component';
import { AdminCategoriasComponent } from './admin-categorias/admin-categorias.component';

import { AdminComponent } from './admin/admin.component';

const appRoutes = [
  { path: 'registro', component: RegistroComponent },
  { path: 'home', component: IndexbodyComponent },
  { path: 'user', component:InfoUserComponent},
  { path: 'listadoProductos/:producto', component: ListadoProductosComponent },
  { path: 'detalleProducto/:id', component: DetalleProductoComponent },
  { path: 'cuenta', component: ModificarCuentaComponent },
  //{ path: '**', redirectTo: '', pathMatch: 'full' },
  { path: 'adminCategorias', component: AdminCategoriasComponent},
  { path: 'administracion', pathMatch: 'prefix', component: AdminComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }

]

export const routing = RouterModule.forRoot(appRoutes);