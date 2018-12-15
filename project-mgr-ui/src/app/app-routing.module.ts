import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ViewComponent} from './view/view.component';
import {AddComponent} from './add/add.component';
import {EditComponent} from './edit/edit.component';

const routes: Routes = [
  {path: 'view', component: ViewComponent},
  {path: 'add', component: AddComponent},
  {path: 'update', component: EditComponent},
  {path: 'update/:id', component: EditComponent},
  {path: '', component: ViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
