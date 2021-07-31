import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

    constructor(private http: HttpClient, private router: Router) { }

    newGame(): void {
        this.http.post<any>('http://localhost:8080/game/', {}).subscribe((response) => {
            this.router.navigate(['/game/', response['id']]);
        });
    }
}
