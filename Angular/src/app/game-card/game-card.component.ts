import {formatDate} from '@angular/common';
import {HttpClient} from '@angular/common/http';
import { Component, HostListener, Input, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Profile} from '../profile';

@Component({
  selector: 'app-game-card',
  templateUrl: './game-card.component.html',
  styleUrls: ['./game-card.component.scss']
})
export class GameCardComponent implements OnInit {
    @Input() game : any;
    winner: String = "";

    constructor(private http: HttpClient, private router: Router) { }
    ngOnInit(): void {
        if (this.game.winner === null) return;

        const url = `http://localhost:8080/user/id/${this.game.winner}`;
        this.http.get<Profile>(url).subscribe(response => this.winner = response.username);
    }

    @HostListener('click', ['$event.target'])
    goToCard() {
        const path = this.game.ended ? '/game/' : '/play/';
        this.router.navigate([path, this.game.id]);
    }

    get getResult() {
        return this.winner ? this.winner : "Egalité";
    }

    get getStartDate() {
        return formatDate(new Date(this.game.creationDate), 'yyyy/mm/dd', 'en');
    }
}
