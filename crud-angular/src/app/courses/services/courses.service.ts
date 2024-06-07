import { Injectable } from '@angular/core';
import { Course } from '../model/course';
import { HttpClient } from '@angular/common/http';
import { first, tap , delay } from 'rxjs';

import { Subscription } from "rxjs";
import { Observer } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(
    private httpClient: HttpClient
  ) { }

  /*list(): Course[] {
    return [
        {   _id: "01" , name: "angular" , category: "front-end from service" }
    ];
  }*/

  //private readonly API = '/assets/courses.json';
  //private readonly API = '/assets/courses.json-test-error';
  //private readonly API = 'http://localhost:8080/api/courses'; //erro de cors
  private readonly API = '/api/courses'; //config proxy

  list() {
    return this.httpClient.get<Course[]>(this.API).pipe(
      first(),
      delay(1000),
      tap(courses => console.log(courses))
      );
  }

  //to save the added course on course-form-component
  save(record: Partial<Course>) {
    //console.log(record)
    return this.httpClient.post<Course>(this.API, record);
  }


}
