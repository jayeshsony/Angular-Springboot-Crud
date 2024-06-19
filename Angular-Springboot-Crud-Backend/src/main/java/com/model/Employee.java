package com.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents an employee entity. Annotated with @Entity for JPA mapping to the
 * 'employee' table. Utilizes Lombok for boilerplate code generation.
 * 
 * @author Jayesh Soni
 * @since 2024-06-04
 */
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "birth_date")
	private String dob;

	@Column(name = "age")
	private int age;

	@Column(name = "address_1")
	private String address1;

	@Column(name = "address_2")
	private String address2;

	@Column(name = "gender")
	private String gender;

	@Column(name = "mobile_number")
	private String mobileNumber;

}