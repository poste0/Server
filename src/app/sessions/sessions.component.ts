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
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.css']
})
export class SessionsComponent implements OnInit {

  constructor() { }

  ELEMENT_DATA: session[] = [

  ];

  displayedColumns = ['film' , 'hall' , 'price'];

  dataSource = new MatTableDataSource<session>(this.ELEMENT_DATA);
  ngOnInit() {
  	this.ELEMENT_DATA.push({
  		film: 'film',
  		hall: 'hall',
  		price: 100
  	});
  	 this.dataSource = new MatTableDataSource<session>(this.ELEMENT_DATA);
  }

}

export interface session{
	film: string;
	hall: string;
	price: number;
}
