package com.example.farmermarket.utils;

import java.util.regex.Pattern;

public class PasswordValidator {

	private static final Pattern LETTER_PATTERN = Pattern.compile("[A-Za-z]");
	private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d");
	private static final Pattern SPACE_PATTERN = Pattern.compile("\\s");
	private static final Pattern SPECIAL_SYMBOL_PATTERN = Pattern.compile("[@#$%^&-+=]");
	
	public static void validate(String password) {
		if(password == null || password.length() < 6)
			throw new IllegalArgumentException("Password must contain at least 6 symbols");
		if(!LETTER_PATTERN.matcher(password).find())
			throw new IllegalArgumentException("Password must contain at least one letter");
		if(!DIGIT_PATTERN.matcher(password).find())
			throw new IllegalArgumentException("Password must contain at least one digit");
		if(SPACE_PATTERN.matcher(password).find()) 
			throw new IllegalArgumentException("Password must be without any space symbols");
		if(!SPECIAL_SYMBOL_PATTERN.matcher(password).find())
			throw new IllegalArgumentException("Password must contain at least one special symbol (@#$%^&-+=)");
		}
	}
	
	

