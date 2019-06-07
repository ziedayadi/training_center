import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {Training} from './training/training';
import {Session} from './training/session';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  public trainingUrl = 'http://localhost:8080/trainings/';

  constructor(private http : HttpClient) { }

  getTrainings() : Observable<Training[]> {
    return this.http.get<Training[]>(this.trainingUrl+'/all');
  }

  save(newTraining: Training) :Observable<Training>{
    return this.http.post<Training>(this.trainingUrl+'/save',newTraining);
  }

  delete(id:number) : Observable<any> {
    return this.http.delete(this.trainingUrl+'/delete/'+id);
  }

  getTraining(id: number):Observable<Training> {
    return this.http.get<Training>(this.trainingUrl+id);
  }

  getSessions(id: number) : Observable<Session[]> {
    return this.http.get<Session[]>(this.trainingUrl+'/'+id+'/'+'sessions');
  }

  saveSession(sessions:Session[]):Observable<Session[]>{
    return this.http.post<Session[]>(this.trainingUrl+'session/save',sessions);
  }
}
