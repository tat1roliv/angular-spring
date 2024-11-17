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


@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss',
})
export class CourseFormComponent {

  form!: FormGroup;

  // form = this.formBuilder.group({
  //   _id: [''],
  //   name: ['', [Validators.required,
  //               Validators.minLength(5),
  //               Validators.maxLength(200)]],
  //   category: ['', [Validators.required]]
  // })


  constructor( private formBuilder: NonNullableFormBuilder,
    public service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute,
   ) {
  }

ngOnInit(): void {
  const course: Course = this.route.snapshot.data['course'];
  // this.form.setValue({_id: course._id, name: course.name, category: course.category})
  // console.log(course);

  this.form = this.formBuilder.group({
    _id: [course._id],
    name: [course.name, [Validators.required,
                Validators.minLength(5),
                Validators.maxLength(200)]],
    category: [course.category, [Validators.required]],
    lessons: this.formBuilder.array(this.retrieveLessons(course))
  });
  console.log(this.form);
  console.log(this.form.value);

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
  name: [lesson.name],
  youtubeUrl: [lesson.youtubeUrl]
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
