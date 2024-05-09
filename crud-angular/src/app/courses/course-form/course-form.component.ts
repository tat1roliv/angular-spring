import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CategoryPipe } from '../../shared/pipes/category.pipe';
import { CoursesService } from '../services/courses.service';

import { Subscription, config } from "rxjs";
import { Observer } from "rxjs";
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss'
})
export class CourseFormComponent {

  form: FormGroup;

  constructor( private formBuilder: FormBuilder,
    public service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location
   ) {
    this.form = this.formBuilder.group({
      name: [null],
      category: [null],
    })
  }

  /*onSubmit(){
    //console.log(this.form.value)
    //return this.service.save(this.form.value).subscribe();
  }*/

  onSubmit() {
    let dataToSubmit = this.form.value;
    //console.log(dataToSubmit)
    this.service.save(dataToSubmit)
    .subscribe(
      //res => console.log(res),
      res => this.onSuccess(),
      error => this.onError()
    );
}

  onCancel(){
    //console.log("cancel")
    this.location.back();
  }

  onSuccess() {
    this._snackBar.open("Saved!");
    this.onCancel()
  }

  private onError(){
    this._snackBar.open("Error on saving")
  }

}
