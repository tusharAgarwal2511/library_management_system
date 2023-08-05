package com.tushar.lms.payloads;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class StudentDto
{
	private UUID      id;
	private String    name;
	private int       age;
	private String    email;
	private String    country;
	private String    phoneNumber;
	private LocalDate createdOn;
	private LocalDate updatedOn;
	private CardDto   card;
}
