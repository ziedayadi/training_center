import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HeroesComponent} from './heroes/heroes.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {HeroDetailComponent} from './hero-detail/hero-detail.component';
import {TrainingComponent} from './training/training.component';
import {TrainingDetailComponent} from './training-detail/training-detail.component';
import {SessionDetailComponent} from './session-detail/session-detail.component';

const routes: Routes = [
  {path: 'heroes', component: HeroesComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'detail/:id', component: HeroDetailComponent},
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path: 'trainings' , component: TrainingComponent},
  {path: 'training/:id' , component: TrainingDetailComponent},
  {path: 'session/:id' , component: SessionDetailComponent}

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})


export class AppRoutingModule {

}
