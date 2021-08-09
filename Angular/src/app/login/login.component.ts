import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    loginForm!: FormGroup;
    isSubmitted = false;

    constructor(public auth: AuthenticationService, private formBuilder: FormBuilder, private router: Router) { }

    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
            valid:    [''],
            username: ['', Validators.required],
            password: ['', Validators.required],
        });
    }

    get formControls() { return this.loginForm.controls; }

    seConnecter() {
        this.isSubmitted = true;
        this.formControls.valid.setErrors(null);
        if (this.loginForm.invalid) return;


        const username: String = this.loginForm.value.username;
        const password: String = this.loginForm.value.password;
        this.auth.login(username, password).subscribe(
            _ => {
                this.auth.setCredentials(username, password);
                this.router.navigate(['/']);
            },
            _ => this.formControls.valid.setErrors({})
        );
    }
}
