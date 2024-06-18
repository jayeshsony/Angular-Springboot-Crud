package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.dto.EmployeeDto;
import com.model.Employee;

/**
 * Service interface for employee-related operations.
 * 
 * This interface defines methods for creating, retrieving, updating, and
 * deleting employees. It also includes methods for converting between DTOs
 * (Data Transfer Objects) and entities.
 * 
 * @author Jayesh Soni
 * @since 2024-06-04
 */
@Repository
public interface EmployeeService {

	/**
	 * Method creates or updates employee record.
	 * 
	 * @param employeeDTO The DTO containing employee information.
	 * @return ResponseEntity representing the HTTP response.
	 */
	public ResponseEntity<?> createOrUpdateEmployee(EmployeeDto employeeDTO);

	/**
	 * Retrieve all employees.
	 * 
	 * @return A list of all employees.
	 */
	public List<Employee> retriveAllEmployees();

	/**
	 * Delete an employee by ID.
	 * 
	 * @param id The ID of the employee to delete.
	 */
	public void deleteEmployee(Long id);

	/**
	 * Get an employee by ID.
	 * 
	 * @param id The ID of the employee to retrieve.
	 * @return The employee with the specified ID.
	 */
	public Employee getEmployeeById(Long id);

}