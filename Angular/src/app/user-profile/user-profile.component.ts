import {HttpClient} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

    constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) { }

    ngOnInit(): void {
        const username = this.activatedRoute.snapshot.params.username;
        const url: string = `http://localhost:8080/user/${username}`;
        this.http.get(url).subscribe(
            (response) => {
                console.log(response);
            },
            (error) => {
                console.error("Error" + error);
            }
        );
    }
}
