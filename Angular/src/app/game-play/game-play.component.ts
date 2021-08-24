import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GameService} from '../game.service';

@Component({
    selector: 'app-game-play',
    templateUrl: './game-play.component.html',
    styleUrls: ['./game-play.component.scss']
})
export class GamePlayComponent implements OnInit {
    gameid!: Number;

    constructor(private activatedRoute: ActivatedRoute, private gameService: GameService, private router: Router) { }

    ngOnInit(): void {
        this.gameid = this.activatedRoute.snapshot.params.gameid;
        if (this.gameid === null) { return; }

        this.gameService.getById(this.gameid).subscribe(
            response => {
                // Si la partie est terminé, on redirige vers le détail de la partie
                if (response.ended) {
                    this.router.navigate(['/game/', this.gameid]);
                }
            }
        );
    }

    play(play: string): void {
        this.gameService.play(this.gameid, play).subscribe(
            _ => this.router.navigate(['/game/', this.gameid]),
            err => alert(err.error.error)
        );
    }
}
