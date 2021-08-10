import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Game} from '../game';

@Component({
    selector: 'app-game-list',
    templateUrl: './game-list.component.html',
    styleUrls: ['./game-list.component.scss']
})
export class GameListComponent implements OnInit {
    data!: Game[];
    constructor(private http: HttpClient) { }

    ngOnInit() {
        this.http.get<Game[]>('http://localhost:8080/game').subscribe(
            response => this.data = response,
            error => console.error("Error" + error.error)
        );
    }
}
