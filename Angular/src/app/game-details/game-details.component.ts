import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {Profile} from '../profile';
import {Game} from '../game';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.scss']
})
export class GameDetailsComponent implements OnInit {
    gameid!: Number;
    gameData!: Game;
    playerData: Profile[] = [];

    constructor(private activatedRoute: ActivatedRoute, private http: HttpClient) { }

    ngOnInit(): void {
        let paramId = this.activatedRoute.snapshot.params.gameid
        if (paramId === null) { return; }

        this.gameid = paramId;

        const gameUrl = `http://localhost:8080/game/${this.gameid}`
        this.http.get<Game>(gameUrl).subscribe((response) => {
            if (response === null) return;
            console.log(response);

            this.gameData = response;
            const player1Url = `http://localhost:8080/user/id/${this.gameData.tour1.player}`;
            this.http.get<Profile>(player1Url).subscribe(response => this.playerData[0] = response);

            const player2Url = `http://localhost:8080/user/id/${this.gameData.tour2.player}`;
            this.http.get<Profile>(player2Url).subscribe(response => this.playerData[1] = response);
        });

    }

    headerClass(player: Number): String {
        if (this.gameData.winner === null) return "draw";
        return player === this.gameData.winner ? "winner" : "loser"
    }

    get username1()  : String { return this.playerData[0]?.username; }
    get username2()  : String { return this.playerData[1]?.username; }
    get classUser1() : String { return this.headerClass(this.gameData.tour1.player); }
    get classUser2() : String { return this.headerClass(this.gameData.tour2.player); }
}
