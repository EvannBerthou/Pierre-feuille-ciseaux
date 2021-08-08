import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthGuardService} from './auth-guard.service';
import {GameDetailsComponent} from './game-details/game-details.component';
import {GamePlayComponent} from './game-play/game-play.component';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {UserProfileComponent} from './user-profile/user-profile.component';

const routes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuardService] },
    { path: 'login', component: LoginComponent },
    { path: 'game', component: GameDetailsComponent },
    { path: 'game/:gameid', component: GameDetailsComponent },
    { path: 'play/:gameid', component: GamePlayComponent },
    { path: 'profile/:username', component: UserProfileComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
