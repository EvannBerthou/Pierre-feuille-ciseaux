import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
    registerForm!: FormGroup;
    isSubmitted: Boolean = false;

    constructor(public auth: AuthenticationService, private formBuilder: FormBuilder) { }

    ngOnInit(): void {
        this.registerForm = this.formBuilder.group({
            username:   ['', Validators.required],
            password:   ['', Validators.required]
        });
    }

    get formControls() { return this.registerForm.controls; }

    senrengistrer() {
        this.isSubmitted = true;
        if (this.registerForm.invalid) return;

        this.auth.register(this.registerForm.value.username, this.registerForm.value.password);
    }
}
