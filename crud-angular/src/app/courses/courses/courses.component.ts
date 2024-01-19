import { Component } from '@angular/core';
import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../shared/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {

  //coursesData: Course[] = [];
  courses$: Observable<Course[]>;

  displayedColumns: string[] = [ "_id", "name", "category" ]

  //coursesService: CoursesService;

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog) {

    //this.coursesService = new CoursesService();
    this.courses$ = this.coursesService.list()
    .pipe(
      catchError(error => {
        //console.log(error)
        this.onError('404')
        return of([])
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

}


