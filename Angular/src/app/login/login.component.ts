import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    constructor(public auth: AuthenticationService) { }

    ngOnInit(): void {
        //this.auth.authicationService('admin', 'admin')
        this.auth.authicationService('user', 'pass')
    }
}
