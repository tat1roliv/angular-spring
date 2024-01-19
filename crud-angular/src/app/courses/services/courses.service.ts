import { Injectable } from '@angular/core';
import { Course } from '../model/course';
import { HttpClient } from '@angular/common/http';
import { first, tap , delay } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(private httpClient: HttpClient) { }

  /*list(): Course[] {
    return [
        {   _id: "01" , name: "angular" , category: "front-end from service" }
    ];
  }*/

  private readonly API = '/assets/courses.json';
  //private readonly API = '/assets/courses.json-test-error';

  list() {
    return this.httpClient.get<Course[]>(this.API).pipe(
      first(),
      delay(5000),
      tap(courses => console.log(courses))
      );
  }
}
