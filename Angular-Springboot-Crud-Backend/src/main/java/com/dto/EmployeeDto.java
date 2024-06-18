package com.dto;

import com.dto.constantmessages.ConstantMessages;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Data Transfer Object (DTO) representing an employee.
 * 
 * This class is used for transferring employee data between layers of the
 * application. It contains fields corresponding to the attributes of an
 * employee.
 * 
 * @author Jayesh Soni
 * @since 2024-06-04
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto {

	private long id;

	@Size(min = 2, max = 50, message = ConstantMessages.FIRST_NAME_SIZE)
	@NotBlank(message = ConstantMessages.FIRST_NAME_REQUIRED)
	private String firstName;

	@Size(min = 2, max = 50, message = ConstantMessages.LAST_NAME_SIZE)
	@NotBlank(message = ConstantMessages.LAST_NAME_REQUIRED)
	private String lastName;

	@Email(message = ConstantMessages.INVALID_EMAIL_FORMAT)
	@NotBlank(message = ConstantMessages.EMAIL_REQUIRED)
	private String email;

	@NotBlank(message = ConstantMessages.DOB_REQUIRED)
	private String dob;

	private int age;

	@Size(min = 2, max = 255, message = ConstantMessages.ADDRESS1_SIZE)
	@NotBlank(message = ConstantMessages.ADDRESS1_REQUIRED)
	private String address1;

	@Size(max = 255, message = ConstantMessages.ADDRESS2_SIZE)
	private String address2;

	@NotBlank(message = ConstantMessages.GENDER_REQUIRED)
	@Size(min = 4, max = 10, message = ConstantMessages.GENDER_SIZE)
	private String gender;

	@NotBlank(message = ConstantMessages.MOBILE_NUMBER_REQUIRED)
	@Pattern(regexp = "\\d{10}", message = ConstantMessages.MOBILE_NUMBER_PATTERN)
	private String mobileNumber;

}