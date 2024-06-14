import { Component, Input, Output , EventEmitter} from '@angular/core';
import { Course } from '../../model/course';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrl: './courses-list.component.scss'
})
export class CoursesListComponent {

  @Input() coursesData: Course[] = [];
  @Output() add: EventEmitter<any> = new EventEmitter();

  readonly displayedColumns: string[] = [ "_id", "name", "category", "actions" ];
  constructor() { }

  onAdd() {
    this.add.emit(true);
  }
}
