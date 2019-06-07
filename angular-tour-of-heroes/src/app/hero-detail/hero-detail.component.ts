import {Component, OnInit} from '@angular/core';
import {Hero} from '../heroes/hero';
import {ActivatedRoute} from '@angular/router';
import {HeroService} from '../hero.service';
import {Location} from '@angular/common';
import {HttpEventType, HttpResponse} from '@angular/common/http';
import {Country} from '../heroes/country';

class ImageSnippet {
  constructor(public src: string, public file: File) {
  }
}

@Component({
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.css']
})
export class HeroDetailComponent implements OnInit {

  //@Input()
  hero: Hero;
  private isEditingPhoto: boolean = false;
  private photo: any;
  private selectedPhoto: File;

  private isEditing:boolean = false;
  private countries:Country[]

  constructor(private route: ActivatedRoute,
              private heroService: HeroService,
              private location: Location) {
  }

  ngOnInit() {
    this.getCoutries();
    this.getHero();
    this.loadPhoto();
  }

  private getHero() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.heroService.getHero(id).subscribe(hero => {
      this.hero = hero;
    });
  }

  goBack() {
    // this.isEditingPhoto = false;
    // this.selectedPhoto = null;
    // this.photo = null;

    this.location.back();
  }

  save() {
    this.heroService.save(this.hero).subscribe(event =>{
        // Now save the image
        if(this.isEditingPhoto){
          this.heroService.savePhoto(this.selectedPhoto, this.hero.id).subscribe ()
        }
        this.goBack();

    })


  }


  onSelectFile(event) {
    const selectedFiles = event.target.files;
    this.selectedPhoto = selectedFiles.item(0);
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      this.photo = reader.result;
    }, false);
    reader.readAsDataURL(this.selectedPhoto);

    this.isEditingPhoto = true;
  }


  private loadPhoto() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.heroService.loadPhoto(id).subscribe(result => {
      this.photo = this.createImageFromBlob(result);
    });


  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener('load', () => {
      this.photo = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  getCoutries() {
    this.heroService.getCountries().subscribe(data=>{
      this.countries = data ;
    });
  }

  onEdit() {
    this.isEditing=true;
  }
}
