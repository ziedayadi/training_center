import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Session} from '../training/session';
import {SessionServiceService} from '../session-service.service';
import {Hero} from '../heroes/hero';
import {HeroService} from '../hero.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-session-detail',
  templateUrl: './session-detail.component.html',
  styleUrls: ['./session-detail.component.css']
})
export class SessionDetailComponent implements OnInit {
  private session: Session;
  private heroes: Hero[];
  private trainers: Hero[];
  selectedHeroes: Hero[]=[];
  selectableHeroes: Hero[]=[];
  isChecked: any;


  constructor(private route: ActivatedRoute, private sessionService: SessionServiceService
    , private heroesService: HeroService,
              private location: Location) {
  }

  ngOnInit() {
    this.getSession();
    this.getHeroes();
    this.getTrainers();

  }

  getSession() {
    let id = +this.route.snapshot.paramMap.get('id');

    this.sessionService.getSessionById(id).subscribe(data => {
      this.session = data;
    });

  }

  getHeroes() {
    let id = +this.route.snapshot.paramMap.get('id');
    this.sessionService.getHeroes(id).subscribe(data => {
      this.heroes = data;
    });
  }

  getTrainers() {
    this.heroesService.getTrainers().subscribe(data => {
      this.trainers = data;
    });
  }

  OnBack() {
    this.location.back();
  }

  onAdd() {
    if(this.selectableHeroes.length==0){
      this.getSelectableHeroes();
    } else {
      this.selectableHeroes = []
      this.selectedHeroes = []
    }


  }

  getSelectableHeroes(){
    console.log(this.heroes.length)

    this.heroesService.getHeros().subscribe(data =>{
      console.log(data.length)
      data.forEach( g=>{
          if(! this.heroes.map(h=>h.id).includes(g.id)){
            this.selectableHeroes.push(g);
          }
      }

      )
    })
  }


  onSelectHero(hero: Hero){
    let i = this.selectedHeroes.indexOf(hero)
    i==-1?this.selectedHeroes.push(hero):this.selectedHeroes.splice(i,1);
    console.log(this.selectedHeroes.length)
  }

  onAddSelected() {
    if(this.selectedHeroes.length>0){
      this.sessionService.addHeroes(this.session.id,this.selectedHeroes).subscribe(()=>{

        this.getHeroes();
        this.selectableHeroes = [];
        this.selectedHeroes = []
        this.getSelectableHeroes();
      })
    }
  }

  onDelete(heroid: number) {
    this.sessionService.deleteHeroFromSession(this.session.id, heroid).subscribe(()=>{
      this.getHeroes();
      this.selectableHeroes=[];
      this.getSelectableHeroes();
    });
  }
}
