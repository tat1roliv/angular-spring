import { Component } from '@angular/core';
import { FormControl,  NonNullableFormBuilder } from '@angular/forms';
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
  styleUrl: './course-form.component.scss'
})
export class CourseFormComponent {

  //form: UntypedFormGroup;
  form = this.formBuilder.group({
    _id: [''],
    name: new FormControl('', {nonNullable: true}),
    category: ['']
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
  //console.log(course);
  this.form.setValue({_id: course._id, name: course.name, category: course.category})
}

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
