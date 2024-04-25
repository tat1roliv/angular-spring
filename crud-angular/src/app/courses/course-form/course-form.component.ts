import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CategoryPipe } from '../../shared/pipes/category.pipe';
import { CoursesService } from '../services/courses.service';

import { Subscription } from "rxjs";
import { Observer } from "rxjs";

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss'
})
export class CourseFormComponent {

  form: FormGroup;

  constructor( private formBuilder: FormBuilder,
    private service: CoursesService
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
    console.log(this.form.value)
    /*return this.service.save(this.form.value).subscribe({
               next: (data) => console.log(data),
               error: () => {
                 this.onError();
               },

    });*/

}
  onCancel(){

  }

}
