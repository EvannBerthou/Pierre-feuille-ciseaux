import {HttpClient} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-game-play',
    templateUrl: './game-play.component.html',
    styleUrls: ['./game-play.component.scss']
})
export class GamePlayComponent implements OnInit {
    gameid: Number | undefined;
    data: any;

    constructor(private activatedRoute: ActivatedRoute, private http: HttpClient, private router: Router) { }

    ngOnInit(): void {
        let paramId = this.activatedRoute.snapshot.params.gameid
        if (paramId === null) { return; }
        this.gameid = paramId;
        this.http.get<any>('http://localhost:8080/game/' + this.gameid).subscribe((response) => {
            this.data = response;
            if (this.data.hasEnded) {
                this.router.navigate(['/game/', this.gameid]); 
            }
        });
    }

    play(play: string): void {
        //TODO: Moyen temporaire de simuler différent joueur
        const playerid = Math.floor(Math.random() * 100);
        const path = `http://localhost:8080/game/${this.gameid}/${playerid}/${play}`;
        this.http.post<any>(path, {}).subscribe(
            _ => this.router.navigate(['/game/', this.gameid])
        );
    }
}
