package com.tushar.lms.controller;

import com.tushar.lms.exceptions.ResourceNotFoundException;
import com.tushar.lms.payloads.ApiResponse;
import com.tushar.lms.payloads.BookDto;
import com.tushar.lms.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book/")
public class BookController
{
	@Autowired
	private BookService bookService;

	@PostMapping("/author/{authorId}")
	public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto, @PathVariable("authorId") UUID authorId) throws ResourceNotFoundException
	{
		BookDto tempBook = bookService.createBook(bookDto, authorId);
		return new ResponseEntity<BookDto>(tempBook, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookDto> getBookById(@PathVariable("id") UUID id) throws ResourceNotFoundException
	{
		BookDto tempBook = bookService.getBookById(id);
		return new ResponseEntity<BookDto>(tempBook, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<BookDto>> getBooks()
	{
		List<BookDto> bookDtos = bookService.getAllBooks();
		return new ResponseEntity<List<BookDto>>(bookDtos, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteBookById(@PathVariable("id") UUID id) throws ResourceNotFoundException
	{
		bookService.deleteBookById(id);
		ApiResponse deleteApiResponse = new ApiResponse();
		deleteApiResponse.setMessage("Author deleted successfully");
		deleteApiResponse.setSuccess(true);
		return new ResponseEntity<ApiResponse>(deleteApiResponse, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookDto> updateBookById(@RequestBody BookDto bookDto, @PathVariable("id") UUID id) throws ResourceNotFoundException
	{
		BookDto tempBook = bookService.updateBookById(bookDto, id);
		return new ResponseEntity<BookDto>(tempBook, HttpStatus.OK);
	}
}
