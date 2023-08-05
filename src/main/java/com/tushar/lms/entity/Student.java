package com.tushar.lms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
public class Student
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID      id;
	private String    name;
	private int       age;
	private String    email;
	private String    country;
	private String    phoneNumber;
	private LocalDate createdOn;
	private LocalDate updatedOn;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Card      card;

}
