package com.tushar.lms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Entity
@Table(name = "authors")
@Data
public class Author
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID   id;
	private String name;
	private String email;
	private int    age;
	private String country;


}
