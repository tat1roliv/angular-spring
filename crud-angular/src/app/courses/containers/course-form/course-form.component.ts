import {ChangeDetectionStrategy, Component, signal} from '@angular/core';
import { FormControl,  FormGroup,  NonNullableFormBuilder, UntypedFormArray, Validators } from '@angular/forms';
import { CategoryPipe } from '../../../shared/pipes/category.pipe';
import { CoursesService } from '../../services/courses.service';

import { Subscription, config } from "rxjs";
import { Observer } from "rxjs";
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { Router } from 'express';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../../model/course';
import { Lesson } from '../../model/lesson';
import { FormUtilsService } from '../../../shared/form/form-utils.service';


@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss',
})
export class CourseFormComponent {
  form!: FormGroup;

  constructor( private formBuilder: NonNullableFormBuilder,
    public service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute,
    public formUtils: FormUtilsService,
   ) {
  }

ngOnInit(): void {
  const course: Course = this.route.snapshot.data['course'];

  this.form = this.formBuilder.group({
    _id: [course._id],
    name: [course.name, [Validators.required,
                Validators.minLength(5),
                Validators.maxLength(200)]],
    category: [course.category, [Validators.required]],
    lessons: this.formBuilder.array(this.retrieveLessons(course), Validators.required)
  });
}

private retrieveLessons(course: Course) {
  const lessons = [];

  if(course?.lessons) {
    course.lessons.forEach(lesson => lessons.push(this.createLesson(lesson)));
  } else { // course is empty
    lessons.push(this.createLesson())
  }

  return lessons;
}

private createLesson(lesson: Lesson = {id:"", name: "", youtubeUrl: ""}) {
return this.formBuilder.group({
  id: [lesson.id],
  name: [lesson.name, [Validators.required,
    Validators.minLength(3),
    Validators.maxLength(100)]],
  youtubeUrl: [lesson.youtubeUrl, [Validators.required,
    Validators.minLength(5),
    Validators.maxLength(100)]]
})
}

getLessonsFormArray() {
  return (<UntypedFormArray>this.form.get('lessons')).controls;
}

addNewLesson():void {
  const lessons = this.form.get('lessons') as UntypedFormArray;
  lessons.push(this.createLesson());
}

removeLesson(index: number) {
  const lessons = this.form.get('lessons') as UntypedFormArray;
  lessons.removeAt(index);
}

onSubmit() {

  if (this.form.valid) {
    let dataToSubmit = this.form.value;
    this.service.save(dataToSubmit)
    .subscribe(
      res => {
        console.log(res),
        this.onSuccess()
      },

      error => {
        console.log(error),
        this.onError()
      }
    );
  } else {
    this.formUtils.validateAllFormFields(this.form);
  }
}

  onCancel(){
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
