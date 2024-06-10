import { Component, Input } from '@angular/core';
import { Course } from '../model/course';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrl: './courses-list.component.scss'
})
export class CoursesListComponent {

  @Input() coursesData: Course[] = [];

  readonly displayedColumns: string[] = [ "_id", "name", "category", "actions" ]
  constructor(
    public router: Router,
    private route: ActivatedRoute,) {

  }

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }
}
