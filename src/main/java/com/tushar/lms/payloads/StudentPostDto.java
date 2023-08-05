package com.tushar.lms.payloads;

import lombok.Data;

@Data
public class StudentPostDto
{
	private String name;
	private int    age;
	private String email;
	private String country;
	private String phoneNumber;
}
