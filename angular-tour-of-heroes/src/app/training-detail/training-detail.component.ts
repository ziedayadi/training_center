import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Training} from '../training/training';
import {TrainingService} from '../training.service';
import {Location} from '@angular/common';
import {Session} from '../training/session';

@Component({
  selector: 'app-training-detail',
  templateUrl: './training-detail.component.html',
  styleUrls: ['./training-detail.component.css']
})
export class TrainingDetailComponent implements OnInit {

  training:Training;
  isEditing: boolean = false;
  private currentEditedTraining : Training;
  newSessions:Session[]= new Array()


  constructor(private route: ActivatedRoute,
              private trainingService:TrainingService,
              private location: Location) { }

  ngOnInit() {
    this.getTraining();
  }

  getTraining(){
    let id = +this.route.snapshot.paramMap.get('id');
    this.trainingService.getTraining(id).subscribe(data =>{
      this.training = data;
      this.getSessions();
    })
  }

  getSessions(){

      this.trainingService.getSessions(this.training.id).subscribe(data => {
        this.training.sessions = data;
      })

  }

  onEdit() {
    this.isEditing = true;
    this.saveEdited();

  }

  private saveEdited(){

    this.currentEditedTraining = new Training();
    this.currentEditedTraining.id=this.training.id;
    this.currentEditedTraining.name=this.training.name
    this.currentEditedTraining.description=this.training.description
    this.currentEditedTraining.period=this.training.period;
    this.currentEditedTraining.prerequisites=this.training.prerequisites;
    this.currentEditedTraining.sessions=this.training.sessions;
  }

  onCancel() {
    this.isEditing=false;
    this.training=this.currentEditedTraining;
  }

  back() {
    this.onCancel();
    this.location.back();
  }

  save() {
    this.trainingService.save(this.training).subscribe(data =>{
      this.training = data;
      this.currentEditedTraining = null;
      this.isEditing=false;
      this.saveSessions();
    })
  }

  onDelete() {
    this.trainingService.delete(this.training.id).subscribe(()=>{
      this.back();
    })
  }


  onAddSession() {
    let s : Session = new Session();
    s.training = this.training;
    this.newSessions.push(s)
  }

  private saveSessions() {
      this.trainingService.saveSession(this.newSessions).subscribe(data=>{
        this.getSessions();
        this.newSessions= new Array();
      })
  }
}
