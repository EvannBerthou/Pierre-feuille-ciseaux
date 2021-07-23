import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-game-list',
    templateUrl: './game-list.component.html',
    styleUrls: ['./game-list.component.scss']
})
export class GameListComponent implements OnInit {
    title = 'Angular';
    data : any;
    constructor(private http: HttpClient) { }

    ngOnInit() {
        this.http.get<any>('http://localhost:8080/game').subscribe((recv) => {
            this.data = recv;
            console.log(this.data);
        });
    }
}
