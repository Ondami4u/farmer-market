package com.inetex.farmermarket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FarmerRegisterRequest {

	private String name;
	@NotBlank
	@Email
	@Pattern(
			regexp = "^[\\w.-]+@(gmail\\.com|mail\\.ru|yandex\\.ru|yandex\\.com|walla\\.co\\.il|hotmail\\.com|yahoo\\.com)$",
			message = "Email should be with recognizable domain, like: gmail.com or mail.ru"
	)
	private String email;
	private String password;

}
