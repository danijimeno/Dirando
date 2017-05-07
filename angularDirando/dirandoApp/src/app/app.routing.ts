import { Routes, RouterModule } from '@angular/router';

import { IndexbodyComponent } from './indexbody/indexbody.component';
import { RegistroComponent } from './registro/registro.component';
import {InfoUserComponent} from './usuario/user.component';
import { ListadoProductosComponent } from './listado-productos/listado-productos.component';
import { DetalleProductoComponent } from './detalle-producto/detalle-producto.component';
import { ModificarCuentaComponent } from './modificar-cuenta/modificar-cuenta.component';

import {ShopComponent} from './shop/shop.component';

import { AdminCategoriasComponent } from './admin-categorias/admin-categorias.component';
import { AdminProductosComponent } from './admin-productos/admin-productos.component';
import { AdminDetalleProductoComponent } from './admin-detalle-producto/admin-detalle-producto.component';
import { AdminAñadirProductoComponent } from './admin-añadir-producto/admin-añadir-producto.component';

import { AdminComponent } from './admin/admin.component';
import { AdminUsuariosComponent } from './admin-usuarios/admin-usuarios.component';


const appRoutes = [
  { path: 'registro', component: RegistroComponent },
  { path: 'home', component: IndexbodyComponent },
  { path: 'user', component:InfoUserComponent},
  { path: 'listadoProductos/:producto', component: ListadoProductosComponent },
  { path: 'detalleProducto/:id', component: DetalleProductoComponent },
  { path: 'cuenta', component: ModificarCuentaComponent },
  {path: 'Cart', component: ShopComponent},
  { path: 'adminProductos', component: AdminProductosComponent },
  { path: 'adminAnadirProducto', component: AdminAñadirProductoComponent },
  { path: 'adminDetalleProducto/:id', component: AdminDetalleProductoComponent },
  { path: 'adminUsuarios', component: AdminUsuariosComponent },
  //{ path: '**', redirectTo: '', pathMatch: 'full' },
  { path: 'adminCategorias', component: AdminCategoriasComponent},
  { path: 'administracion', pathMatch: 'prefix', component: AdminComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }

]

export const routing = RouterModule.forRoot(appRoutes);