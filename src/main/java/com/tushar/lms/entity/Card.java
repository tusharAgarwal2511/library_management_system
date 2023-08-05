package com.tushar.lms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "cards")
@Data
public class Card
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID      id;
	private String    email;
	private String    status;
	private LocalDate createdOne;
	private LocalDate updatedOn;
	private LocalDate validUpTo;
}
