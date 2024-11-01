import {ChangeDetectionStrategy, Component, signal} from '@angular/core';
import { FormControl,  NonNullableFormBuilder, Validators } from '@angular/forms';
import { CategoryPipe } from '../../../shared/pipes/category.pipe';
import { CoursesService } from '../../services/courses.service';

import { Subscription, config } from "rxjs";
import { Observer } from "rxjs";
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { Router } from 'express';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../../model/course';


@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss',
})
export class CourseFormComponent {

  form = this.formBuilder.group({
    _id: [''],
    name: ['', [Validators.required,
                Validators.minLength(5),
                Validators.maxLength(200)]],
    category: ['', [Validators.required]]
  })


  constructor( private formBuilder: NonNullableFormBuilder,
    public service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute,
   ) {
    //this.form = this.formBuilder.group({
      //name: [null],
      //category: [null],
    //})
  }

ngOnInit(): void {
  const course: Course = this.route.snapshot.data['course'];
  this.form.setValue({_id: course._id, name: course.name, category: course.category})
  console.log(course)
}

  onSubmit() {
    let dataToSubmit = this.form.value;
    //console.log(dataToSubmit)
    this.service.save(dataToSubmit)
    .subscribe(
      // res => this.onSuccess(),
      // error => this.onError()
      res => {
        console.log(res),
        this.onSuccess()
      },

      error => {
        console.log(error),
        this.onError()
      }
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

  public getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

    if(field?.hasError('required')) {
      return 'This fild is required';
    }

    if(field?.hasError('minlength')) {
      const requiredLength = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Min length is ${requiredLength} characters`;
    }

    if(field?.hasError('minlength')) {
      const requiredLength = field.errors ? field.errors['maxlength']['requiredLength'] : 200;
      return `Max length is ${requiredLength} characters`;
    }

    return 'Error';
  }


}
