import {formatDate} from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {Profile} from '../profile';
import {Game} from '../game';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.scss']
})
export class GameDetailsComponent implements OnInit {
    gameid!: Number;
    gameData!: Game;
    playerData: Profile[] = [];

    constructor(private activatedRoute: ActivatedRoute, private http: HttpClient, private auth: AuthenticationService) { }

    ngOnInit(): void {
        let paramId = this.activatedRoute.snapshot.params.gameid;
        if (paramId === null) { return; }

        this.gameid = paramId;

        const gameUrl = `http://localhost:8080/game/${this.gameid}`;
        this.http.get<Game>(gameUrl).subscribe((response) => {
            if (response === null) return;
            console.log(response);

            this.gameData = response;

            if (this.gameData.tour1 !== null) {
                const player1Url = `http://localhost:8080/user/id/${this.gameData.tour1.player}`;
                    this.http.get<Profile>(player1Url).subscribe(response => this.playerData[0] = response);
            }

            if (this.gameData.tour2 !== null) {
                const player2Url = `http://localhost:8080/user/id/${this.gameData.tour2.player}`;
                this.http.get<Profile>(player2Url).subscribe(response => this.playerData[1] = response);
            }
        });
    }

    isWinner(player: Number): boolean {
        return player === this.gameData.winner;
    }

    headerClass(player: Number): String {
        if (this.gameData.winner === null) return "draw";
        return this.isWinner(player) ? "winner" : "loser"
    }

    get username1()  : String { return this.playerData[0]?.username; }
    get username2()  : String { return this.playerData[1]?.username; }
    get classUser1() : String { return this.headerClass(this.gameData.tour1?.player); }
    get classUser2() : String { return this.headerClass(this.gameData.tour2?.player); }
    get getResult() { 
        if (!this.gameData.ended) return "En cours";
        if (this.gameData.winner === null ) return "Egalité";

        const selfUsername = this.auth.username;

        if (selfUsername === "") return "Terminée";

        if (selfUsername == this.username1) {
            return this.isWinner(this.gameData.tour1.player) ? "Gagnant" : "Perdant";
        }

        if (selfUsername == this.username2) {
            return this.isWinner(this.gameData.tour2.player) ? "Gagnant" : "Perdant";
        }
        return "";
    }

    get getStartDate() { return formatDate(new Date(this.gameData.creationDate), 'yyyy/MM/dd', 'en'); }

}
