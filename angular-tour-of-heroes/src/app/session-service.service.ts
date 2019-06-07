import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Training} from './training/training';
import {Session} from './training/session';
import {Hero} from './heroes/hero';

@Injectable({
  providedIn: 'root'
})
export class SessionServiceService {

  public trainingUrl = 'http://localhost:8080/trainings/';
  public sessionsUrl = 'http://localhost:8080/sessions/';
  public heroesUrl = 'http://localhost:8080/heroes/';

  constructor(private http : HttpClient) {

  }

  getSessionById(id:number) : Observable<Session> {
    return this.http.get<Session>(this.sessionsUrl+id);
  }

  getHeroes(id: number): Observable<Hero[]>  {
    console.log(this.heroesUrl+'/session/'+id)
    return this.http.get<Hero[]>(this.heroesUrl+'/session/'+id);
  }

  addHeroes(id: number, selectedHeroes: Hero[]):Observable<any> {
      return this.http.post(this.sessionsUrl+'/'+id,selectedHeroes)
  }

  deleteHeroFromSession(sessionId:number, heroId:Number):Observable<any>{
    return this.http.delete(this.sessionsUrl+'/'+sessionId+'?hero='+heroId);
  }
}
