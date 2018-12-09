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
import * as _moment from 'moment';
const moment = _moment;
@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.css']
})
export class SessionsComponent implements OnInit {

  constructor(public dialog: MatDialog , public http : HttpClient) { }
  ELEMENT_DATA: object[] = [

  ];

  displayedColumnsForSessions = ['film' , 'time' , 'price' , 'Capacity' , 'book'];
  displayedColumnsForFilms = ['title' , 'description'];
  isSession = true;
  isEvents = false;
  dataSource = new MatTableDataSource<session>(this.ELEMENT_DATA);
  ngOnInit() {
    this.getAllSessions(100 , 1);
  }
  switchFilms(){
    this.isSession = false;
    this.isEvents = false;
      this.getAllFilms(100 , 1);

  }
  switchSession(){
    this.isEvents = false;
    this.isSession = true;
    this.getAllSessions(100 , 1);
  }

  switchEvents(){
    this.isEvents = true;
    this.getAllEvents();
  }

  getAllEvents(){
    this.http.get('http://localhost:8081/external/events').subscribe(data => {
        this.parseEvents(data);
      });
  }
  getAllFilms(size: number , page: number){
    this.http.get('http://localhost:8081/external/films?size=' + size+'&page=' + page).subscribe(data => {
        this.parseFilms(data);
      });
  }
  parseFilms(data) {
   //var data_size = data[0];
   //data = Object.values(data[1]);
   //data = JSON.stringify(data);
   this.ELEMENT_DATA = []
   Object.values(data).forEach(item => {
     this.ELEMENT_DATA.push({
       title: item.title,
       description: item.description
     });
   });
   /*if (data_size % this.size == 0) {
     this.pages = parseInt(data_size / this.size);
   }
   else {
     this.pages = parseInt(data_size / this.size) + 1;
   }*/
   this.dataSource = new MatTableDataSource<film>(this.ELEMENT_DATA);
 }

  getAllSessions(size: number , page: number){
    this.http.get('http://localhost:8081/external/sessions?size=' + size+'&page=' + page).subscribe(data => {
        this.parseSession(data);
      });
  }

  parseSession(data) {
   //var data_size = data[0];
   //data = Object.values(data[1]);
   //data = JSON.stringify(data);
   this.ELEMENT_DATA = []
   Object.values(data).forEach(item => {
     this.ELEMENT_DATA.push({
       id: item.id,
       film: item.film.title,
       time: moment(item.time).format('hh:mm DD.MM.YYYY'),
       price: item.cost,
       capacity: item.capacity//moment(item.date).format('hh:mm DD.MM.YYYY'),
     });
   });
   /*if (data_size % this.size == 0) {
     this.pages = parseInt(data_size / this.size);
   }
   else {
     this.pages = parseInt(data_size / this.size) + 1;
   }*/
   this.dataSource = new MatTableDataSource<session>(this.ELEMENT_DATA);
 }
 isNumber(value: string | number): boolean
 {
    return !isNaN(Number(value.toString()));
 }


 parseEvents(data){

   this.ELEMENT_DATA = []
   Object.values(data).forEach(item => {
     var result: string = ['' , '' , '' , '' , ''];
     var condition = item.condition;
   if(condition.split(',')[2] == '%'){
     result[4] = 'Percent';
   }
   else{
     result[4] = 'Digit';
   }
   result[3] = condition.split(',')[1];
   result[2] = condition.split(',')[0].split(' ')[1];
   if(this.isNumber(condition[4]){
     result[0] = 'Age';
     if(condition[3] == '<'){
       result[1] = 'Less';
     }
     else if(condition[3] == '<='){
       result[1] = 'Not more';
     }
     else if(condition[3] == '=='){
       result[1] = 'Equals';
     }
     else if(condition[3] == '>'){
       result[1] = 'More';
     }
     else if(condition[3] == '>='){
       result[1] = 'Not less';
     }
   }
   else{
     result[0] = 'Bought Tickets';
     if(condition[5] == '<'){
       result[1] = 'Less';
     }
     else if(condition[5] == '<='){
       result[1] = 'Not more';
     }
     else if(condition[5] == '=='){
       result[1] = 'Equals';
     }
     else if(condition[5] == '>'){
       result[1] = 'More';
     }
     else if(condition[5] == '>='){
       result[1] = 'Not less';
     }

   }
     this.ELEMENT_DATA.push({
      condition: 'For people with ' + result[0] + ' ' + result[1] + ' ' + result[2] + ' Less ' + result[3] + ' By ' + result[4] ,
      endDate: item.endDate//moment(item.date).format('hh:mm DD.MM.YYYY'),
     });
   });
   /*if (data_size % this.size == 0) {
     this.pages = parseInt(data_size / this.size);
   }
   else {
     this.pages = parseInt(data_size / this.size) + 1;
   }*/
   this.dataSource = new MatTableDataSource<event>(this.ELEMENT_DATA);
 }

 createBook(row){
    var index = this.dataSource.data.indexOf(row);
    prompt(this.ELEMENT_DATA[index].id);
   let dialogRef = this.dialog.open(DialogOverviewDialog, {
     data:{id: this.ELEMENT_DATA[index].id }
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

  constructor(private http: HttpClient,
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  createBook(name: string , email: string , age: number){

    prompt(this.data.id);
    this.http.post(
      'http://localhost:8081/external/order/?sessionId='+this.data.id+'&ticketsCount=1',
      JSON.stringify({
        name: name,
        email: email,
        age: age,
        boughtTickets : 0,
      })
      , {
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

export interface session{
  id: long;
	film: string;
	time: Date;
	price: number;
  capacity: number;
}

export interface film{
  title: string;
  description: string;
}

export interface event{
  condition: string;
  endDate: string;
}
