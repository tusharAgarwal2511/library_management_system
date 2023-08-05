package com.tushar.lms.service.book;

import com.tushar.lms.exceptions.ResourceNotFoundException;
import com.tushar.lms.payloads.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface BookService
{
	BookDto createBook(BookDto bookDto, UUID authorId) throws ResourceNotFoundException;

	BookDto getBookById(UUID id) throws ResourceNotFoundException;

	List<BookDto> getAllBooks();

	BookDto updateBookById(BookDto bookDto, UUID id) throws ResourceNotFoundException;

	void deleteBookById(UUID id) throws ResourceNotFoundException;
}
