package com.tushar.lms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "books")
@Data
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID    id;
	private String  name;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Author  author;
	private int     numberOfPages;
	private String  language;
	private boolean available;
	private String  genre;
	private String  isbnNumber;
	private String  publishedDate;

}
