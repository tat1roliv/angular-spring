import { Component, Input, Output , EventEmitter} from '@angular/core';
import { Course } from '../model/course';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrl: './courses-list.component.scss'
})
export class CoursesListComponent {

  @Input() coursesData: Course[] = [];
  //@Output() add = new EventEmitter(isAsync: false);
  @Output() add: EventEmitter<boolean> = new EventEmitter(false);

  readonly displayedColumns: string[] = [ "_id", "name", "category", "actions" ];
  constructor(
    //public router: Router,
    // private route: ActivatedRoute,
   ) { }

  onAddComponent() {
    //this.router.navigate(['new'], {relativeTo: this.route});
    this.add.emit(true);
  }
}
