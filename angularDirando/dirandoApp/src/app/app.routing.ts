import { Routes, RouterModule } from '@angular/router';

import { IndexbodyComponent } from './indexbody/indexbody.component';
import { RegistroComponent } from './registro/registro.component';
import {InfoUserComponent} from './usuario/user.component';

const appRoutes = [
  { path: 'registro', component: RegistroComponent },
  { path: 'home', component: IndexbodyComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'user', component:InfoUserComponent}
]

export const routing = RouterModule.forRoot(appRoutes);