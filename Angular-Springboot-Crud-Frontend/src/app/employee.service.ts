import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private currentEmployee: Employee = new Employee();

  constructor(private http: HttpClient) { }

  // Method to fetch all employees from the server
  getAllEmployees(): Observable<Employee[]> {
    // Make an HTTP GET request to the API endpoint that retrieves all employees and expect an array of Employee objects in response
    return this.http.get<Employee[]>('http://localhost:8080/retrive');
  }

  // Method to send new employee data to the server
  postEmployeeData(employeeData: any): Observable<any> {
    // Make an HTTP POST request to the API endpoint for creating a new employee and send the provided employeeData in the request body
    return this.http.post('http://localhost:8080/create', employeeData);
  }

  // Method to delete an employee by their ID
  deleteUser(userId: number): Observable<any> {
    // Make an HTTP DELETE request to the API endpoint for deleting an employee with the provided userId
    return this.http.delete(`http://localhost:8080/delete/${userId}`);
  }

  // Method to fetch an employee by their ID
  getEmployeeById(id: number): Observable<Employee> {
    // Make an HTTP GET request to the API endpoint for fetching an employee with the provided id and expect a single Employee object in response
    return this.http.get<Employee>(`http://localhost:8080/emp/${id}`);
  }

  // Method to set the current employee to the provided employee object
  setCurrentEmployee(employee: any): void {
    this.currentEmployee = employee;
  }

  // Method to get the current employee to the provided employee object
  getCurrentEmployee(): any {
    return this.currentEmployee;
  }

  // Method to update an employee's data
  updateEmployee(updatedData: any): Observable<any> {
    // Make an HTTP PUT request to the API endpoint for updating an employee with the provided id and send the updatedData in the request body
    return this.http.put<Employee>(`http://localhost:8080/update`, updatedData);
  }

}