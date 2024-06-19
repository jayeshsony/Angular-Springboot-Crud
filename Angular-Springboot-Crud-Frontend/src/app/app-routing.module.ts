import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  {
    path: '', redirectTo: 'retrive', pathMatch: 'full'
  },
  {
    path: 'create',
    component: RegistrationFormComponent,
  },
  {
    path: 'goback',
    redirectTo: 'create',
    pathMatch: 'prefix',
  },
  {
    path: 'retrive',
    component: EmployeeListComponent,
  },
  {
    path: '404',
    component: PageNotFoundComponent,
  },
  {
    path: '**', // If there's no matching path found then it will redirect to page not found component
    redirectTo: '404',
    pathMatch: 'prefix',
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }