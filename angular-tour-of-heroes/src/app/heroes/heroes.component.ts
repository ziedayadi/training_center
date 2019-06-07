import {Component, OnInit} from '@angular/core';
import {Hero} from './hero';
import {HeroService} from '../hero.service';
import {Country} from './country';
import {FormControl, FormGroup, Validators} from '@angular/forms';


function forbiddenNameValidator(regExp: RegExp) {
  return undefined;
}

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  heros: Hero[];
  nameKw: string = '';
  newHero: Hero = new Hero();
  countries: Country[];
  errorMessages: String[];
  private heroForm: FormGroup;


  constructor(private heroService: HeroService) {
  }

  ngOnInit() {
    this.getHeros();
    this.getCoutries();
  }

  getHeros() {
    this.heroService.getHeros().subscribe(heros => {
      this.heros = heros;
    });
  };

  add() {

      this.heroService.create(this.newHero).subscribe(e =>{
         this.heros.push(e)
        this.newHero = new Hero();
      });
  }


  getFilteredHeroes(): Hero[] {
    return this.heros != null ? this.heros.filter(h => (h.name.toLowerCase().includes(this.nameKw.toLowerCase()) ||(h.lastName.toLowerCase().includes(this.nameKw.toLowerCase()) ))).sort((a, b) => {
      if (a.name < b.name) {
        return -1;
      }
      if (a.name > b.name) {
        return 1;
      }
      return 0;
    }) : this.heros;
  }

  getCoutries() {
    this.heroService.getCountries().subscribe(data=>{
      this.countries = data ;
    });
  }


}
