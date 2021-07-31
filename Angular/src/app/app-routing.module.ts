import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GameDetailsComponent} from './game-details/game-details.component';
import {GamePlayComponent} from './game-play/game-play.component';
import {HomeComponent} from './home/home.component';

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'game', component: GameDetailsComponent },
    { path: 'game/:gameid', component: GameDetailsComponent },
    { path: 'play/:gameid', component: GamePlayComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
