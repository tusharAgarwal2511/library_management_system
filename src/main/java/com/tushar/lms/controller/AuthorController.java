package com.tushar.lms.controller;

import com.tushar.lms.exceptions.ResourceNotFoundException;
import com.tushar.lms.payloads.ApiResponse;
import com.tushar.lms.payloads.AuthorDto;
import com.tushar.lms.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/author/")
public class AuthorController
{
	@Autowired
	private AuthorService authorService;

	@PostMapping("/")
	public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto)
	{
		AuthorDto tempAuthorDto = authorService.createAuthor(authorDto);
		return new ResponseEntity<AuthorDto>(tempAuthorDto, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") UUID id) throws ResourceNotFoundException
	{
		AuthorDto tempAuthorDto = authorService.getAuthorById(id);
		return new ResponseEntity<AuthorDto>(tempAuthorDto, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<AuthorDto>> getAllAuthors()
	{
		List<AuthorDto> tempAuthorDtos = authorService.getAllAuthors();
		return new ResponseEntity<List<AuthorDto>>(tempAuthorDtos, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AuthorDto> updateAuthorById(@PathVariable("id") UUID id, @RequestBody AuthorDto authorDto) throws ResourceNotFoundException
	{
		AuthorDto tempAuthorDto = authorService.updateAuthorById(authorDto, id);
		return new ResponseEntity<AuthorDto>(tempAuthorDto, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteAuthorById(@PathVariable("id") UUID id) throws ResourceNotFoundException
	{
		authorService.deleteAuthorById(id);
		ApiResponse deleteApiResponse = new ApiResponse();
		deleteApiResponse.setMessage("Author deleted successfully");
		deleteApiResponse.setSuccess(true);
		return new ResponseEntity<ApiResponse>(deleteApiResponse, HttpStatus.OK);
	}

}
