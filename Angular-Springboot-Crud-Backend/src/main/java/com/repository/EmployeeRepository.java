package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Employee;

/**
 * Repository interface for performing CRUD operations on Employee entities.
 * 
 * Extends JpaRepository for JPA-specific methods and inherits basic CRUD
 * operations from CrudRepository. Defines custom methods for checking email and
 * mobile number field existence, optionally excluding a specific ID.
 * 
 * @author Jayesh Soni
 * @since 2024-06-04
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	/**
	 * Check if an employee exists with the given email.
	 * 
	 * @param email The email to check.
	 * @return true if an employee exists with the given email, false otherwise.
	 */
	boolean existsByEmail(String email);

	/**
	 * Check if an employee exists with the given mobile number.
	 * 
	 * @param mobileNumber The mobile number to check.
	 * @return true if an employee exists with the given mobile number, false
	 *         otherwise.
	 */
	boolean existsByMobileNumber(String mobileNumber);

	/**
	 * Check if an employee exists with the given email, excluding the specified ID.
	 * 
	 * @param email The email to check.
	 * @param id    The ID to exclude from the check.
	 * @return true if an employee exists with the given email (excluding the
	 *         specified ID), false otherwise.
	 */
	boolean existsByEmailAndIdNot(String email, Long id);

	/**
	 * Check if an employee exists with the given mobile number, excluding the
	 * specified ID.
	 * 
	 * @param mobileNumber The mobile number to check.
	 * @param id           The ID to exclude from the check.
	 * @return true if an employee exists with the given mobile number (excluding
	 *         the specified ID), false otherwise.
	 */
	boolean existsByMobileNumberAndIdNot(String mobileNumber, Long id);

}