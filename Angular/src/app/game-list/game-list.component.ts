import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-game-list',
    templateUrl: './game-list.component.html',
    styleUrls: ['./game-list.component.scss']
})
export class GameListComponent implements OnInit {
    data: [] = [];
    constructor(private http: HttpClient) { }

    ngOnInit() {
        this.http.get<any>('http://localhost:8080/game').subscribe(
            response => this.data = response,
            error => console.error("Error" + error)
        );
    }
}
