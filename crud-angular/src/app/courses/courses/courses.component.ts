import { Component } from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import { Course } from '../model/course';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {

  coursesData: Course[] = [
    {   _id: "01" , name: "angular" , category: "front-end" }
  ];

  displayedColumns: string[] = [ "_id", "name", "category" ]
  dataSource = this.coursesData;

  constructor() {}

}


