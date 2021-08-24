import {formatDate} from '@angular/common';
import { Component, HostListener, Input, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {GameProfilesService} from '../game-profiles.service';
import {Profile} from '../profile';

@Component({
  selector: 'app-game-card',
  templateUrl: './game-card.component.html',
  styleUrls: ['./game-card.component.scss']
})
export class GameCardComponent implements OnInit {
    @Input() game : any;
    winner: String = "";
    playerData: Profile[] = [];

    constructor(private router: Router, private gameProfiles: GameProfilesService) { }
    ngOnInit(): void {
        this.playerData = this.gameProfiles.getGameProfiles(this.game);
    }

    @HostListener('click', ['$event.target'])
    goToCard() {
        const path = this.game.ended ? '/game/' : '/play/';
        this.router.navigate([path, this.game.id]);
    }

    isWinner(player: Number): boolean {
        return player === this.game.winner;
    }

    headerClass(player: Number): String {
        if (this.game.winner === null) return "draw";
        return this.isWinner(player) ? "winner" : "loser"
    }

    get username1()  : String { return this.playerData[0]?.username; }
    get username2()  : String { return this.playerData[1]?.username; }
    get classUser1() : String { return this.headerClass(this.game.tour1?.player); }
    get classUser2() : String { return this.headerClass(this.game.tour2?.player); }

    get getResult() {
        if (this.game.ended === false) return "En cours";
        if (this.game.winner === null) return "Egalité";

        if (this.game.winner === this.game.tour1.player)
            return this.playerData[0]?.username;

        if (this.game.winner === this.game.tour2.player)
            return this.playerData[1]?.username;

        return "?";
    }

    get getStartDate() {
        return formatDate(new Date(this.game.creationDate), 'yyyy/mm/dd', 'en');
    }
}
