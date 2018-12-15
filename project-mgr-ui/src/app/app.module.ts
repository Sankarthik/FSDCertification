import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule, MatNativeDateModule } from '@angular/material';
import { TaskService } from './service/task.service';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import {AppComponent} from './app.component';
import {ViewComponent} from './view/view.component';
import {AddComponent} from './add/add.component';
import {AppRoutingModule} from './app-routing.module';
import {EditComponent} from './edit/edit.component';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {TaskFilter} from './pipes/task-filter.pipe';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    ViewComponent,
    AddComponent,
    EditComponent,
    TaskFilter
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    MatDatepickerModule,
    MatNativeDateModule,
    BrowserAnimationsModule
  ],
  exports: [
    MatDatepickerModule,
    MatNativeDateModule,
    BrowserAnimationsModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [TaskService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
