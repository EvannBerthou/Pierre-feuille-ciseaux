import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameListComponent } from './game-list/game-list.component';
import { GameCardComponent } from './game-card/game-card.component';
import { GameDetailsComponent } from './game-details/game-details.component';
import { GameDetailsMoveComponent } from './game-details-move/game-details-move.component';
import { HomeComponent } from './home/home.component';
import { GamePlayComponent } from './game-play/game-play.component';
import {HttpInterceptorInterceptor} from './http-interceptor.interceptor';
import {AuthenticationService} from './authentication.service';
import { LoginComponent } from './login/login.component';
import {AuthGuardService} from './auth-guard.service';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { RegisterComponent } from './register/register.component';
import { NavbarComponent } from './navbar/navbar.component';


@NgModule({
  declarations: [
    AppComponent,
    GameListComponent,
    GameCardComponent,
    GameDetailsComponent,
    GameDetailsMoveComponent,
    HomeComponent,
    GamePlayComponent,
    LoginComponent,
    UserProfileComponent,
    RegisterComponent,
    NavbarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
      {
          provide: HTTP_INTERCEPTORS,
          useClass: HttpInterceptorInterceptor,
          multi: true
      },
      AuthenticationService,
      AuthGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
