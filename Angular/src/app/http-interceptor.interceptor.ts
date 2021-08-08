import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpInterceptor,
    HttpHeaders,
    HttpEvent
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class HttpInterceptorInterceptor implements HttpInterceptor {

    constructor(private authicationService: AuthenticationService) {}

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        if (this.authicationService.username === "") { return next.handle(request); }
        const clone = request.clone({
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': this.authicationService.getAuth()
            })
        });
        console.log(clone);
        return next.handle(clone);
    }
}

