import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    loginForm!: FormGroup;
    isSubmitted = false;

    constructor(public auth: AuthenticationService, private formBuilder: FormBuilder) { }

    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        //this.auth.authicationService('admin', 'admin')
    }

    get formControls() { return this.loginForm.controls; }

    seConnecter() {
        this.isSubmitted = true;
        if (this.loginForm.invalid) return;

        this.auth.authicationService(this.loginForm.value.username, this.loginForm.value.password);
    }
}
