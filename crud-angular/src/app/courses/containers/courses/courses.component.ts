import { Component } from '@angular/core';
import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { validateVerticalPosition } from '@angular/cdk/overlay';
import { ConfirmationDialogComponent } from '../../../shared/components/confirmation-dialog/confirmation-dialog.component';



@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {

  //coursesData: Course[] = [];
  courses$: Observable<Course[]> | null = null;

  displayedColumns: string[] = [ "_id", "name", "category", "actions" ]

  //coursesService: CoursesService;

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    public router: Router,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar) {
    //this.courses$ = this.coursesService.list()
    //.pipe(
      //catchError(error => {
        //this.onError('404')
        //return of([])
      //})
    //);
    this.refresh();
  }

  refresh(){

    // const temp = this.coursesService.list()
    // console.log(temp)

    this.courses$ = this.coursesService.list()
    .pipe(

      catchError(error => {
        //console.log(error)
        this.onError('404')
        return of([])
      }),
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg

    });
  }

  onAdd() {
    //console.log("add function")
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  onEdit(course: Course){
    //console.log("edit function")
    this.router.navigate(['edit', course._id ], {relativeTo: this.route});
  }

  onRemove(course: Course){
    //to dialog
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: "do you want to remove this course?",
    });

    dialogRef.afterClosed().subscribe(result => {
        if (result) {
          //removing
          this.coursesService.remove(course._id).subscribe(() => {
            this._snackBar.open('Removed!');
            this.refresh();
        },
          error => {
            this.onError('Error: course not removed!')
          });
        }
    });

  }

}


