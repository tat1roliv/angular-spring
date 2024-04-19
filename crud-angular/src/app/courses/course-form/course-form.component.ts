import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CategoryPipe } from '../../shared/pipes/category.pipe';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss'
})
export class CourseFormComponent {

  form: FormGroup;

  constructor( private formBuilder: FormBuilder ) {
    this.form = this.formBuilder.group({
      name: [null],
      category: [null],
    })
  }

  onSubmit(){

  }
  onCancel(){
    
  }

}
