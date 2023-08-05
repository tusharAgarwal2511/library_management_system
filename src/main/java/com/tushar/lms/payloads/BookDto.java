package com.tushar.lms.payloads;

import lombok.Data;

import java.util.UUID;

@Data
public class BookDto
{
	private UUID      id;
	private String    name;
	private AuthorDto author;
	private int       numberOfPages;
	private String    language;
	private boolean   available;
	private String    genre;
	private String    isbnNumber;
	private String    publishedDate;
}
