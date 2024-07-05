import { Component, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrl: './confirmation-dialog.component.scss'

})

export interface DialogData {
  firstName: string;
  lastName: string;
 }


export class ConfirmationDialogComponent {

  readonly dialogRef = inject(MatDialogRef<ConfirmationDialogComponent>);
  readonly data  = inject<DialogData>(MAT_DIALOG_DATA) ;

  onConfirm(result: boolean): void {
    this.dialogRef.close(result);
  }

}
