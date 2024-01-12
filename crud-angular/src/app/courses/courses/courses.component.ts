import { Component } from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {

  coursesData: Course[] = [];

  displayedColumns: string[] = [ "_id", "name", "category" ]

  coursesService: CoursesService;

  constructor() {
    this.coursesService = new CoursesService();
    this.coursesData = this.coursesService.list();
  }

}


