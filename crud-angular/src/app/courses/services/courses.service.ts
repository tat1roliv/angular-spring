import { Injectable } from '@angular/core';
import { Course } from '../model/course';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor() { }

  list(): Course[] {
    return [
        {   _id: "01" , name: "angular" , category: "front-end from service" }
    ];
  }
}
