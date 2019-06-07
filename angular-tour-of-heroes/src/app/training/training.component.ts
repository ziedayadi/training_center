import { Component, OnInit } from '@angular/core';
import {TrainingService} from '../training.service';
import {Training} from './training';
import {Router} from '@angular/router';

@Component({
  selector: 'app-training',
  templateUrl: './training.component.html',
  styleUrls: ['./training.component.css']
})
export class TrainingComponent implements OnInit {
  private trainings: Training[];
  newTraining: Training;
  keyWord: string = '';


  constructor(private  trainigService : TrainingService,
              private router:Router,
              ) { }

  ngOnInit() {
    this.getTrainings();
  }

  getTrainings(){
    this.trainigService.getTrainings().subscribe(
      data => {this.trainings = data}
    )
  }

  onClick(id) {
    this.router.navigateByUrl('training/'+id);
  }

  onAdd() {
    this.newTraining = new Training();
  }

  onSave() {
    this.trainigService.save(this.newTraining).subscribe( data =>{
        this.trainings.push(data)
      this.newTraining = null;
    });
  }

  onCancel() {
    this.newTraining = null;
  }

  getFileredList():Training[]{
    let result : Training[];
    if(this.trainings == null ){
      result = this.trainings;
    } else {
      result = this.trainings.filter(t => t.name.toLowerCase().includes(this.keyWord.toLowerCase()) || t.description.toLowerCase().includes(this.keyWord.toLowerCase()) || t.reference.toLowerCase().includes(this.keyWord.toLowerCase()) )
    }

    return result

  }

}
