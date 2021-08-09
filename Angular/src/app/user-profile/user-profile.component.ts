import {HttpClient} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
    data: any;
    playedGames: any;

    constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) { }

    ngOnInit(): void {
        const username = this.activatedRoute.snapshot.params.username;

        const urlStats: string = `http://localhost:8080/user/${username}`;
        this.http.get(urlStats).subscribe(
            response => this.data = response,
            error => console.error("Error" + error)
        );

        const urlGames: string = `http://localhost:8080/games/${username}`;
        this.http.get(urlGames).subscribe(response => this.playedGames = response);
    }

    get username()  { return this.data?.username;  }
    get totalGame() { return this.data?.totalGame; }
    get winCount()  { return this.data?.winCount;  }
    get loseCount() { return this.data?.loseCount; }
}
