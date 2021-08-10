import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../authentication.service';
import {Game} from '../game';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
    constructor(private http: HttpClient, private router: Router, public auth: AuthenticationService) { }

    newGame(): void {
        if (this.auth.isAuthenticated === false) {
            this.router.navigate(['/login']);
            return;
        }

        this.http.post<Game>('http://localhost:8080/game/', {}).subscribe(
            response => this.router.navigate(['/play/', response['id']])
        );
    }
}
