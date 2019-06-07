import {Injectable} from '@angular/core';
import {Hero} from './heroes/hero';
import {Observable, of} from 'rxjs';
import {MessageService} from './message.service';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
import {catchError, map, tap} from 'rxjs/operators';
import {Country} from './heroes/country';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable({
  providedIn: 'root'
})


export class HeroService {

  public heroesUrl = 'http://localhost:8080/heroes';
  public countriesUrl = 'http://localhost:8080/countries/all';


  constructor(private messageService: MessageService,
              private http: HttpClient) {
  }

  getHeros(): Observable<Hero[]> {
    this.log('HeroService: fetched heroes');
    return this.http.get<Hero[]>(this.heroesUrl).pipe(tap(_ => {
        this.log('fetched heroes');
      }),
      catchError(this.handleError<Hero[]>('getHeroes', []))
    );
  }


  getHero(id: number): Observable<Hero> {
    this.log(`HeroService: fetched hero id=${id}`);
    return this.http.get<Hero>(this.heroesUrl + '/' + id);
    //return of(HEROES.find(hero => hero.id === id));
  }

  private log(msg: string) {
    this.messageService.add(msg);
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  save(hero: Hero): Observable<any> {
    return this.http.patch(this.heroesUrl + '/' + hero.id, hero, httpOptions).pipe(
      tap(_ => this.log(`updated hero id=${hero.id}`)),
      catchError(this.handleError<any>('updateHero'))
    );
  }

  create(hero: Hero): Observable<any> {
    return this.http.post(this.heroesUrl, hero);
  }

  /** POST: add a new hero to the server */
  addHero(hero: Hero): Observable<Hero> {
    return this.http.post<Hero>(this.heroesUrl, hero, httpOptions).pipe(
      tap((newHero: Hero) => this.log(`added hero w/ id=${newHero.id}`)),
      catchError(this.handleError<Hero>('addHero'))
    );
  }


  deleteHero(id: number): Observable<any> {
    const _url = this.heroesUrl + '/' + id;
    this.log('Deleted Hero ' + id);
    return this.http.delete(_url);

  }

  savePhoto(file: File, id): Observable<HttpEvent<{}>> {

    let formData: FormData = new FormData();
    formData.append('file', file);

    const req = new HttpRequest('POST', this.heroesUrl + '/uploadPhoto/' + id, formData, {reportProgress: true, responseType: 'text'});
    return this.http.request(req);
  }


  loadPhoto(id: number): Observable<Blob> {

    return this.http.get(this.heroesUrl + '/photo/' + id, {responseType: 'blob'});
  }

  getCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(this.countriesUrl);
  }

  getTrainers(): Observable<Hero[]> {
    return this.http.get<Hero[]>(this.heroesUrl + '/trainers');
  }
}
