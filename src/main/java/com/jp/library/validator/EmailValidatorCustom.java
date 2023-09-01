package com.jp.library.validator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EmailValidatorCustom {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	public static boolean validate(String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
