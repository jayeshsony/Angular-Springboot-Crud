package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EmployeeDto;
import com.model.Employee;
import com.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Controller class for handling HTTP requests related to employee management.
 * 
 * This class defines endpoints for creating, retrieving, updating, and deleting
 * employee records. It handles requests for CRUD operations on Employee
 * entities.
 * 
 * @author Jayesh Soni
 * @since 2024-06-04
 */
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {

	EmployeeService employeeService;

	/**
	 * Handle POST requests to create a new employee record.
	 * 
	 * @param employeeDTO The DTO representing the employee to be created.
	 * @return ResponseEntity containing the result of the operation.
	 */
	@PostMapping("/create")
	public ResponseEntity<?> handleForm(@RequestBody @Valid EmployeeDto employeeDTO) {
		return employeeService.createOrUpdateEmployee(employeeDTO);
	}

	/**
	 * Handle GET requests to retrieve all employee records.
	 * 
	 * @return List of all employee records.
	 */
	@GetMapping("/retrive")
	public List<Employee> retriveEmployees() {
		return employeeService.retriveAllEmployees();
	}

	/**
	 * Handle DELETE requests to delete an employee record by ID.
	 * 
	 * @param id The ID of the employee to delete.
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
	}

	/**
	 * Handle GET requests to retrieve an employee record by ID.
	 * 
	 * @param id The ID of the employee to retrieve.
	 * @return ResponseEntity containing the retrieved employee record.
	 */
	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}

	/**
	 * Handle PUT requests to update an employee record
	 * 
	 * @param employeeDTO The DTO representing the updated employee data.
	 * @return ResponseEntity containing the result of the update operation.
	 */
	@PutMapping("/update")
	public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeDto employeeDTO) {
		return employeeService.createOrUpdateEmployee(employeeDTO);
	}

	/**
	 * Exception handler for MethodArgumentNotValidException that extracts field
	 * errors and creates a map of field names with error messages, returning them
	 * in a ResponseEntity.
	 * 
	 * @param currentException the MethodArgumentNotValidException
	 * @return ResponseEntity<Map<String, String>> containing field errors
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException currentException) {
		Map<String, String> errorsMap = new HashMap<>();

		currentException.getBindingResult().getFieldErrors().forEach(error -> {
			String field = error.getField();
			String message = error.getDefaultMessage();
			errorsMap.putIfAbsent(field, message);
		});
		return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
	}

}