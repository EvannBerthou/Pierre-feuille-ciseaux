import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameListComponent } from './game-list/game-list.component';
import { GameCardComponent } from './game-card/game-card.component';
import { GameDetailsComponent } from './game-details/game-details.component';
import { GameDetailsMoveComponent } from './game-details-move/game-details-move.component';


@NgModule({
  declarations: [
    AppComponent,
    GameListComponent,
    GameCardComponent,
    GameDetailsComponent,
    GameDetailsMoveComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
