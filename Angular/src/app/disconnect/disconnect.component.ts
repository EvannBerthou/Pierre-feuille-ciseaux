import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-disconnect',
  templateUrl: './disconnect.component.html',
  styleUrls: ['./disconnect.component.scss']
})
export class DisconnectComponent implements OnInit {
    constructor(private router: Router, private auth: AuthenticationService) { }

    ngOnInit(): void {
        this.auth.disconnect();
        this.router.navigate(['/']);
    }
}
