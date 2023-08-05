package com.tushar.lms.service.author;

import com.tushar.lms.exceptions.ResourceNotFoundException;
import com.tushar.lms.payloads.AuthorDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AuthorService
{
	AuthorDto createAuthor(AuthorDto authorDto) throws ResourceNotFoundException;

	AuthorDto updateAuthorById(AuthorDto authorDto, UUID id) throws ResourceNotFoundException;

	AuthorDto getAuthorById(UUID id) throws ResourceNotFoundException;

	void deleteAuthorById(UUID id) throws ResourceNotFoundException;

	List<AuthorDto> getAllAuthors() throws ResourceNotFoundException;

}
