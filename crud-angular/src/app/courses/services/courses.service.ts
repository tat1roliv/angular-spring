import { Injectable } from '@angular/core';
import { Course } from '../model/course';
import { HttpClient } from '@angular/common/http';

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

  list() {
    return this.httpClient.get<Course[]>(this.API)
  }
}
