import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthGuardService} from './auth-guard.service';
import {DisconnectComponent} from './disconnect/disconnect.component';
import {GameDetailsComponent} from './game-details/game-details.component';
import {GamePlayComponent} from './game-play/game-play.component';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {UserProfileComponent} from './user-profile/user-profile.component';

const routes: Routes = [
    { path: '', component: HomeComponent},
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'disconnect', component: DisconnectComponent },
    { path: 'game', component: GameDetailsComponent },
    { path: 'game/:gameid', component: GameDetailsComponent },
    { path: 'play/:gameid', component: GamePlayComponent, canActivate: [AuthGuardService] },
    { path: 'profile/:username', component: UserProfileComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
