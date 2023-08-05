package com.tushar.lms.payloads;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthorDto
{
	private UUID   id;
	private String name;
	private String email;
	private int    age;
	private String country;
}
