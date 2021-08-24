import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Game} from './game';
import {Profile} from './profile';

@Injectable({
    providedIn: 'root'
})
export class GameProfilesService {
    constructor(private http: HttpClient) { }

    getGameProfiles(game: Game): Profile[] {
        let playerData: Profile[] = [];
        if (game.tour1 !== null) {
            const player1Url = `http://localhost:8080/user/id/${game.tour1.player}`;
            this.http.get<Profile>(player1Url)
                .subscribe(response => playerData[0] = response);
        }

        if (game.tour2 !== null) {
            const player2Url = `http://localhost:8080/user/id/${game.tour2.player}`;
            this.http.get<Profile>(player2Url)
                .subscribe(response => playerData[1] = response);
        }

        return playerData;
    }
}
