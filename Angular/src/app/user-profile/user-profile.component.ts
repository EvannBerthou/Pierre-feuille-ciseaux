import {HttpClient} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Game} from '../game';
import {Profile} from '../profile';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
    profile!: Profile;
    playedGames!: Game[];

    constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) { }

    ngOnInit(): void {
        const username = this.activatedRoute.snapshot.params.username;

        const urlStats: string = `http://localhost:8080/user/${username}`;
        this.http.get<Profile>(urlStats).subscribe(
            response => this.profile = response,
            error => console.error("Error" + error)
        );

        const urlGames: string = `http://localhost:8080/games/${username}`;
        this.http.get<Game[]>(urlGames).subscribe(response => this.playedGames = response);
    }

    get username()  { return this.profile?.username;  }
    get totalGame() { return this.profile?.totalGame; }
    get winCount()  { return this.profile?.winCount;  }
    get loseCount() { return this.profile?.loseCount; }
}
