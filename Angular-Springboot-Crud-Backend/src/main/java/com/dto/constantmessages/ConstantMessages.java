package com.dto.constantmessages;

/**
 * This class contains constant messages used for validation purposes.
 *
 * @author Jayesh Soni
 * @since 2024-06-10
 */
public class ConstantMessages {

	public static final String FIRST_NAME_REQUIRED = "First name is required";
	public static final String FIRST_NAME_SIZE = "First name must be between 2 and 50 characters";

	public static final String LAST_NAME_REQUIRED = "Last name is required";
	public static final String LAST_NAME_SIZE = "Last name must be between 2 and 50 characters";

	public static final String EMAIL_REQUIRED = "Email is required";
	public static final String INVALID_EMAIL_FORMAT = "Invalid email format";

	public static final String DOB_REQUIRED = "Date of Birth is required";

	public static final String AGE_REQUIRED = "Age is required";

	public static final String ADDRESS1_REQUIRED = "Address line 1 is required";
	public static final String ADDRESS1_SIZE = "Address line 1 must be between 5 and 100 characters";

	public static final String ADDRESS2_SIZE = "Address line 2 cannot exceed 100 characters";

	public static final String GENDER_REQUIRED = "Gender is required";
	public static final String GENDER_SIZE = "Gender must be between 4 and 10 characters";

	public static final String MOBILE_NUMBER_REQUIRED = "Mobile number is required";
	public static final String MOBILE_NUMBER_PATTERN = "Mobile number must be 10 digits";

	public static final String USER_NOT_FOUND = "User not found";

	private ConstantMessages() {
		throw new IllegalStateException("ConstantMessages class should not be instantiated.");
	}

}