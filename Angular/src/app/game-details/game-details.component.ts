import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {Profile} from '../profile';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.scss']
})
export class GameDetailsComponent implements OnInit {
    gameid: Number | undefined;
    gameData: any;
    playerData: Profile[] = [];

    constructor(private activatedRoute: ActivatedRoute, private http: HttpClient) { }

    ngOnInit(): void {
        let paramId = this.activatedRoute.snapshot.params.gameid
        if (paramId === null) { return; }

        this.gameid = paramId;
        this.http.get<any>('http://localhost:8080/game/' + this.gameid).subscribe((response) => {
            if (response === null) return;

            this.gameData = response;
            this.http.get<Profile>('http://localhost:8080/user/id/' + this.gameData.tour1.player).subscribe(
                response => this.playerData[0] = response
            );

            this.http.get<Profile>('http://localhost:8080/user/id/' + this.gameData.tour2.player).subscribe(
                response => this.playerData[1] = response
            );
        });

    }

    headerClass(player: Number) {
        if (this.gameData.winner === null) return "draw";
        return player === this.gameData.winner ? "winner" : "loser"
    }

    get username1()  { return this.playerData[0]?.username; }
    get username2()  { return this.playerData[1]?.username; }
    get classUser1() { return this.headerClass(this.gameData.tour1.player); }
    get classUser2() { return this.headerClass(this.gameData.tour2.player); }
}
