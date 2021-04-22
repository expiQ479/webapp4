import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Game } from '../interfaces/game.model';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

const BASE_URL: string = 'api/games/';
@Injectable({providedIn: 'root' })
  export class GameService {
    constructor(private httpClient: HttpClient) { }

    getGameById(id: number): Observable<Game>{
        return this.httpClient.get(BASE_URL + id).pipe(
          catchError(error => this.handleError(error))
        ) as Observable<Game>;
    }

    private handleError(error: any) {
		console.error(error);
		return Observable.throw('Server error (' + error.status + '): ' + error.text())
	}


  }