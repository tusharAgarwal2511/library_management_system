package com.tushar.lms.service.author;

import com.tushar.lms.entity.Author;
import com.tushar.lms.exceptions.ResourceNotFoundException;
import com.tushar.lms.payloads.AuthorDto;
import com.tushar.lms.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService
{
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public AuthorDto createAuthor(AuthorDto authorDto)
	{
		Author authorToSave = modelMapper.map(authorDto, Author.class);
		authorRepository.save(authorToSave);
		return modelMapper.map(authorToSave, AuthorDto.class);
	}

	@Override
	public AuthorDto updateAuthorById(AuthorDto authorDto, UUID id) throws ResourceNotFoundException
	{
		Author tempAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "Author id", id));
		tempAuthor.setName(authorDto.getName());
		tempAuthor.setEmail(authorDto.getEmail());
		tempAuthor.setAge(authorDto.getAge());
		tempAuthor.setCountry(authorDto.getCountry());
		authorRepository.save(tempAuthor);
		return modelMapper.map(tempAuthor, AuthorDto.class);
	}

	@Override
	public AuthorDto getAuthorById(UUID id) throws ResourceNotFoundException
	{
		Author tempAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "Author id", id));
		return modelMapper.map(tempAuthor, AuthorDto.class);
	}

	@Override
	public void deleteAuthorById(UUID id) throws ResourceNotFoundException
	{
		Author tempAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "Author id", id));
		authorRepository.delete(tempAuthor);

	}

	@Override
	public List<AuthorDto> getAllAuthors()
	{
		List<Author>    authors    = authorRepository.findAll();
		List<AuthorDto> authorDtos = authors.stream().map(author -> modelMapper.map(author, AuthorDto.class)).collect(Collectors.toList());
		return authorDtos;

	}
}
