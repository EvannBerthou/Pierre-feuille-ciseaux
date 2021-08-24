import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Game} from './game';

@Injectable({
    providedIn: 'root'
})
export class GameService {

    constructor(private http: HttpClient) { }

    getById(id: Number) {
        const gameUrl = `http://localhost:8080/game/${id}`;
        return this.http.get<Game>(gameUrl);
    }

    play(gameid: Number, play: String) {
        const url = `http://localhost:8080/game/${gameid}/${play}`;
        return this.http.post<Game>(url, {});
    }
}
