package com.tushar.lms.payloads;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CardDto
{
	private UUID      id;
	private String    email;
	private String    status;
	private LocalDate createdOne;
	private LocalDate updatedOn;
	private LocalDate validUpTo;
}
