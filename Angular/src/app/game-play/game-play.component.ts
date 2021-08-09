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

        const url = `http://localhost:8080/game/${this.gameid}`;
        this.http.get<any>(url).subscribe(
            response => {
                this.data = response;
                if (this.data.ended) {
                    this.router.navigate(['/game/', this.gameid]);
                }
            }
        );
    }

    play(play: string): void {
        const url = `http://localhost:8080/game/${this.gameid}/${play}`;
        this.http.post<any>(url, {}).subscribe(
            _ => this.router.navigate(['/game/', this.gameid])
        );
    }
}
