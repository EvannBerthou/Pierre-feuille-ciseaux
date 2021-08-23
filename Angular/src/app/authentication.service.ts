import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthenticationService {
    public username: String = "";
    public password: String = "";
    public isAuthenticated: boolean = false;

    constructor(private http: HttpClient) { }

    setCredentials(u: String, p: String) {
        this.username = u;
        this.password = p;
        this.isAuthenticated = true;
    }

    login(user: String, pass: String) {
        return this.http.post('http://localhost:8080/login', {
            username: user,
            password: pass
        });
    }

    register(username: String, password: String) {
        this.http.post('http://localhost:8080/register', {
            username: username,
            password: password
        }).subscribe(response => {
            if (response === true) {
                this.setCredentials(username, password);
            }
        })
    }

    createAuthToken(user: String, pass: String) {
        return 'Basic ' + window.btoa(user + ":" + pass);
    }

    public getAuth() {
        return this.createAuthToken(this.username, this.password);
    }

    public disconnect() {
        this.username = "";
        this.password=  "";
        this.isAuthenticated = false;
    }
}
