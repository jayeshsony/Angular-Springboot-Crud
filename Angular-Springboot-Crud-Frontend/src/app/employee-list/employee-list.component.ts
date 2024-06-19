import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent {

  employees: Employee[] = [];
  employeeIdToDelete: number = 0;
  employeeName: string = '';
  showConfirmationDialog = false;

  constructor(private _snackBar: MatSnackBar, private router: Router, private empService: EmployeeService) { }

  ngOnInit(): void {
    // When the component initializes, retrieve users
    this.getUsers();
  }

  // Method to fetch users from the service
  private getUsers() {
    this.empService.getAllEmployees().subscribe(data => this.employees = data);
  }

  // Method to navigate to the update page for a specific employee
  updateEmployee(id: number): void {
    this.empService.getEmployeeById(id).subscribe({
      next: (employee) => {
        // Save the employee data
        this.empService.setCurrentEmployee(employee);
        this.router.navigate(['/create']);
      }
    });
  }

  // Properties to manage confirmation dialog
  confirmDelete(employeeId: number, employeeName: string) {
    this.employeeIdToDelete = employeeId;
    this.employeeName = employeeName;
    this.showConfirmationDialog = true;
  }

  // Method to delete employee after confirmation
  deleteEmployee() {
    this.empService.deleteUser(this.employeeIdToDelete).subscribe({
      next: () => {
        this.employees = this.employees.filter(user => user.id !== this.employeeIdToDelete);
        // Showing success message on when employee record deleted
        this.openSnackBar(this.employeeName);
      }
    });
    this.closeConfirmationDialog();
  }

  // Method to open a snackbar indicating that a user has been deleted
  openSnackBar(employeeName: string): void {
    this._snackBar.open(`${employeeName}'s record has been deleted successfully.`, '', { duration: 2000 });
  }

  // Method to close the confirmation dialog
  closeConfirmationDialog() {
    this.showConfirmationDialog = false;
  }

}