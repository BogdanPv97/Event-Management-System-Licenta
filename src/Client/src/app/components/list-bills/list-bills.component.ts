import { Component, OnInit } from '@angular/core';
import { Bill } from 'src/app/common/bill';
import { BillService } from 'src/app/service/bill.service';

@Component({
  selector: 'app-list-bills',
  templateUrl: './list-bills.component.html',
  styleUrls: ['./list-bills.component.css'],
})
export class ListBillsComponent implements OnInit {
  bills: Bill[];

  constructor(private billService: BillService) {}

  ngOnInit(): void {
    this.getAllBillsForUser();
  }

  getAllBillsForUser() {
    this.billService.getAllBillsForUser(38).subscribe((result) => {
      this.bills = result;
    });
  }
}
