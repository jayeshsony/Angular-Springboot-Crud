import { Component, OnDestroy, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Employee } from '../employee';
import { numberLengthValidator } from '../validator';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {

  public registrationForm !: FormGroup;

  // Object to hold employee data
  private employee: Employee = new Employee();

  // Flag to control whether to show the update button
  public showUpdateButton: boolean = false;

  // Flag to indicate if the selected date is the future date
  public isFutureDate: boolean = false;

  // Flag to check data is changed in update form
  public isFormChanged: boolean = false;

  // Error messages for email and mobile number
  emailErrorMessage: string = '';
  mobileErrorMessage: string = '';

  // Store back-end validations
  fieldErrors: any = {};

  constructor(
    private _snackBar: MatSnackBar,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private empService: EmployeeService
  ) { }

  ngOnInit(): void {
    // Get the data of current employee
    this.employee = this.empService.getCurrentEmployee();

    this.registrationForm = this.formBuilder.group({
      id: [''],
      firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      lastName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      email: ['', [Validators.required, Validators.email]],
      dob: ['', [Validators.required]],
      age: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      mobileNumber: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10), numberLengthValidator(10, 10)]],
      gender: ['', [Validators.required]],
      address1: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      address2: ['', [Validators.minLength(2), Validators.maxLength(255)]]
    });

    this.registrationForm.valueChanges.subscribe(() => {
      this.isFormChanged = true;
    });

    // If employee data is available, populate the form with the employee's details
    if (this.employee.id !== 0) {
      this.registrationForm.patchValue({
        id: this.employee.id,
        firstName: this.employee.firstName,
        lastName: this.employee.lastName,
        email: this.employee.email,
        dob: this.employee.dob,
        age: this.employee.age,
        mobileNumber: this.employee.mobileNumber,
        gender: this.employee.gender,
        address1: this.employee.address1,
        address2: this.employee.address2
      });
      this.showUpdateButton = true;
    }
  }

  // Method to check if the selected date is the future date
  checkDate() {
    const currentDate: Date = new Date();
    const selectedDate: Date = new Date((<HTMLInputElement>document.getElementById('dob')).value);
    this.isFutureDate = selectedDate > currentDate;
  }

  // Method to handle form submission
  onSubmit(): void {
    this.employee = this.registrationForm.value;
    this.employee.id ? this.updateEmployeeData() : this.insertEmployeeData();
  }

  // Method to navigate user to EmployeeList page
  cancleAction() {
    this.router.navigate(['/retrive']);
  }

  // Method to submit new employee data
  insertEmployeeData() {
    this.empService.postEmployeeData(this.employee).subscribe({
      next: () => this.handleSuccessResponse(),
      error: (error) => this.handleErrorResponse(error)
    });
    this.registerInputValueChanges();
  }

  // Method to update employee data
  updateEmployeeData(): void {
    this.empService.updateEmployee(this.employee).subscribe({
      next: () => this.handleSuccessResponse(),
      error: (error) => this.handleErrorResponse(error)
    });
    this.registerInputValueChanges();
  }

  // Method to handle errors while inserting and updating record
  handleErrorResponse(error: any): void {
    const text = error.error.text;
    if (text === 'bothExist' || text === 'emailExists' || text === 'mobileExists') {
      this.emailErrorMessage = text === 'bothExist' || text === 'emailExists' ? 'Email already exists.' : '';
      this.mobileErrorMessage = text === 'bothExist' || text === 'mobileExists' ? 'Mobile number already exists.' : '';
    } else {
      this.fieldErrors = error.error;
    }
  }

  // Method to check changes in email and mobile number
  registerInputValueChanges() {
    this.registrationForm.controls['email'].valueChanges.subscribe(() => this.emailErrorMessage = '');
    this.registrationForm.controls['mobileNumber'].valueChanges.subscribe(() => this.mobileErrorMessage = '');
  }

  // Method to display snackbar after successfull insertion or updation
  handleSuccessResponse(): void {
    this.emailErrorMessage = '';
    this.mobileErrorMessage = '';
    const employeeName = this.employee.firstName;
    const message = this.employee.id ? `${employeeName}, your record has been updated.` : `${employeeName}, your record has been inserted.`;
    this._snackBar.open(message, '', { duration: 2000, panelClass: ['custom-snack-bar'] });
    this.registrationForm.reset();
    this.router.navigate(['/retrive']);
  }

  ngOnDestroy() {
    // Clear the current employee data when the component is destroyed
    this.empService.setCurrentEmployee(null);
  }

}