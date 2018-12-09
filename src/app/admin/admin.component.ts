import { Component, OnInit, ViewChild, Inject, Output, Input } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatCheckboxModule } from '@angular/material';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormControl } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { SelectionModel } from '@angular/cdk/collections';
import { MatSnackBar } from '@angular/material/snack-bar';
import {MatDividerModule} from '@angular/material/divider';
import {MatListModule} from '@angular/material/list';
import {MatSidenav} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material';
import {Router, ActivatedRoute, Params } from "@angular/router";
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }
  createEvent(){
    let dialogRef = this.dialog.open(DialogOverviewDialog, {
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

}
@Component({
  selector: 'dialog-overview',
  templateUrl: './dialog-overview.html',
  styleUrls: ['./dialog-overview.css']
})
export class DialogOverviewDialog {

  constructor(private http: HttpClient , public dialogRef: MatDialogRef<DialogOverviewDialog> ,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }
  types = [
    {value: 'AGE'},
    {value: 'TOTAL'}
  ];

  mles = [
    {value: '<'},
    {value: '='},
    {value: '>'},
    {value: '<='},
    {value: '>='}
  ];

  typeOfEvents = [
    {value: '%' , msg: 'Percents'},
    {value: '!' , msg: 'By digit'}
  ];

  createEvent(type , mle , value , cost ,  typeOfEvent , date){

    let endDate = +new Date(date);
    prompt(endDate);
    this.http.post(
      'http://localhost:8081/internal/events/event=' + type + mle + ' ' + value + ',' + cost + ',' + typeOfEvent + '&date=' + endDate ,
       {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Access-Control-Allow-Headers': 'Content-Type',
          'Access-Control-Allow-Origin': '*'
        }),
        observe: 'response'
      }).subscribe(data => JSON.stringify(data));
  }

}
