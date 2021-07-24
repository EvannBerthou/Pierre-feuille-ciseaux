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

    gameDetails() {
        this.router.navigate(['/game/', this.game.id]);
    }
}
