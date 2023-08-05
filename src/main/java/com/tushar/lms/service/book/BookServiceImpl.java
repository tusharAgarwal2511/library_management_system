package com.tushar.lms.service.book;

import com.tushar.lms.entity.Author;
import com.tushar.lms.entity.Book;
import com.tushar.lms.exceptions.ResourceNotFoundException;
import com.tushar.lms.payloads.BookDto;
import com.tushar.lms.repository.AuthorRepository;
import com.tushar.lms.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService
{
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public BookDto createBook(BookDto bookDto, UUID authorId) throws ResourceNotFoundException
	{
		Author author   = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "Author id", authorId));
		Book   tempBook = modelMapper.map(bookDto, Book.class);
		tempBook.setAuthor(author);
		Book savedBook = bookRepository.save(tempBook);
		return modelMapper.map(savedBook, BookDto.class);
	}

	@Override
	public BookDto getBookById(UUID id) throws ResourceNotFoundException
	{
		Book tempBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "Book id", id));
		return modelMapper.map(tempBook, BookDto.class);
	}

	@Override
	public List<BookDto> getAllBooks()
	{
		List<Book>    books    = bookRepository.findAll();
		List<BookDto> bookDtos = books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
		return bookDtos;
	}

	@Override
	public BookDto updateBookById(BookDto bookDto, UUID id) throws ResourceNotFoundException
	{
		Book tempBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "Book id", id));
		tempBook.setName(bookDto.getName());
		tempBook.setAvailable(bookDto.isAvailable());
		tempBook.setGenre(bookDto.getGenre());
		tempBook.setNumberOfPages(bookDto.getNumberOfPages());
		tempBook.setLanguage(bookDto.getLanguage());
		tempBook.setIsbnNumber(bookDto.getIsbnNumber());
		tempBook.setPublishedDate(bookDto.getPublishedDate());
		bookRepository.save(tempBook);
		return modelMapper.map(tempBook, BookDto.class);
	}

	@Override
	public void deleteBookById(UUID id) throws ResourceNotFoundException
	{
		Book tempBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "Book id", id));
		bookRepository.delete(tempBook);
	}
}
