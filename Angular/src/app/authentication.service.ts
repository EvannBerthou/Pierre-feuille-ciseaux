import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Router} from '@angular/router';

@Injectable()
export class AuthenticationService {
    public username: String = "";
    public password: String = "";
    public isAuthenticated: boolean = false;

    constructor(private http: HttpClient, private router: Router) { }

    authicationService(user: String, pass: String) {
        return this.http.post('http://localhost:8080/login', {
            username: user,
            password: pass
        }).subscribe(response => {
            if (response === true) {
                this.username = user;
                this.password = pass;
            }
            this.router.navigate(['/']);
            this.isAuthenticated = Boolean(response);
        })
    }

    createAuthToken(user: String, pass: String) {
        return 'Basic ' + window.btoa(user + ":" + pass);
    }

    public getAuth() {
        return this.createAuthToken(this.username, this.password);
    }
}
