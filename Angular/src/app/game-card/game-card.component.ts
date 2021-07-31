import { Component, Input, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-game-card',
  templateUrl: './game-card.component.html',
  styleUrls: ['./game-card.component.scss']
})
export class GameCardComponent implements OnInit {
    @Input() game : any;
    constructor(private router: Router) { }
    ngOnInit(): void { }

    goToCard() {
        if (this.game.hasEnded) {
            this.router.navigate(['/game/', this.game.id]);
        } else {
            this.router.navigate(['/play/', this.game.id]);
        }
    }
}
