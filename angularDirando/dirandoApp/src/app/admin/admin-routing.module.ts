import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdminComponent } from './admin.component';
import { AdminCategoriasComponent } from './../admin-categorias/admin-categorias.component';

const routes: Routes = [
  { path: 'administracion', component: AdminComponent},
  { path: 'adminCategorias', component: AdminCategoriasComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
