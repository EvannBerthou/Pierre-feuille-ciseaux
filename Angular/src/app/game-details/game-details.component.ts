import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.scss']
})
export class GameDetailsComponent implements OnInit {
    gameid: Number | undefined;
    data: any;

    classesHeaders: string[] = [];

    constructor(private activatedRoute: ActivatedRoute, private http: HttpClient) { }

    ngOnInit(): void { 
        let paramId = this.activatedRoute.snapshot.params.gameid
        if (paramId !== null) {
            this.gameid = paramId;
            this.http.get<any>('http://localhost:8080/game/' + this.gameid).subscribe((recv) => {
                this.data = recv;
                if (this.data.hasEnded) {
                    // S'il y a égalité
                    if (this.data.gagnant == null) {
                        this.classesHeaders = ["draw", "draw"];
                    } else {
                        this.classesHeaders[0] = this.data.player1.player === this.data.gagnant ? "winner" : "looser";
                        this.classesHeaders[1] = this.data.player2.player === this.data.gagnant ? "winner" : "looser";
                    }
                }
            });
        }
    }
}
