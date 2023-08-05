package com.tushar.lms.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException
{
	String resourceName;
	String fieldName;
	UUID   fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, UUID fieldValue)
	{
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue.toString()));
		this.resourceName = resourceName;
		this.fieldName    = fieldName;
		this.fieldValue   = fieldValue;
	}
}
