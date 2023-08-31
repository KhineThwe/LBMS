package com.jp.library.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
	@Override
	public void initialize(ValidPhone constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.trim().isEmpty()) {
			// Phone number is blank, show the "PhoneNo cannot be blank!" message
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("PhoneNo cannot be blank!").addConstraintViolation();
			return false;
		}

		// Check if the phone number matches the pattern
		if (value.matches("^(09[0-9]{7,15}|0[1-9][0-9]{8,14})$")) {
			return true;
		}

		// Phone number is not blank but doesn't match the pattern, show the "Invalid
		// phone number format" message
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("Invalid phone number format").addConstraintViolation();
		return false;
	}
}
