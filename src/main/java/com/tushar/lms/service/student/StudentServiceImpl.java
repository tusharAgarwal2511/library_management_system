package com.tushar.lms.service.student;

import com.tushar.lms.entity.Card;
import com.tushar.lms.entity.Student;
import com.tushar.lms.exceptions.ResourceNotFoundException;
import com.tushar.lms.payloads.StudentDto;
import com.tushar.lms.payloads.StudentPostDto;
import com.tushar.lms.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/***
 * @author Tushar Agarwal
 */

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StudentDto createStudent(StudentPostDto studentPostDto)
	{
		final int CARD_VALIDATION_IN_YEARS = 3;

		Student tempStudent = modelMapper.map(studentPostDto, Student.class);
		tempStudent.setCreatedOn(LocalDate.now());
		tempStudent.setUpdatedOn(LocalDate.now());

		Card tempCard = new Card();
		tempCard.setEmail(tempStudent.getEmail());
		tempCard.setUpdatedOn(LocalDate.now());
		tempCard.setCreatedOne(LocalDate.now());
		tempCard.setStatus("Active");
		tempCard.setValidUpTo(LocalDate.now().plusYears(CARD_VALIDATION_IN_YEARS));

		tempStudent.setCard(tempCard);
		studentRepository.save(tempStudent);
		return modelMapper.map(tempStudent, StudentDto.class);
	}

	@Override
	public StudentDto getStudentById(UUID id) throws ResourceNotFoundException
	{
		Student tempStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Student id", id));
		return modelMapper.map(tempStudent, StudentDto.class);
	}

	@Override
	public List<StudentDto> getAllStudents()
	{
		List<Student>    students    = studentRepository.findAll();
		List<StudentDto> studentDtos = students.stream().map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
		return studentDtos;
	}

	@Override
	public StudentDto updateStudentById(StudentPostDto studentPostDto, UUID id) throws ResourceNotFoundException
	{
		Student tempStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Student id", id));
		tempStudent.setName(studentPostDto.getName());
		tempStudent.setAge(studentPostDto.getAge());
		tempStudent.setEmail(studentPostDto.getEmail());
		tempStudent.setCountry(studentPostDto.getCountry());
		tempStudent.setPhoneNumber(studentPostDto.getPhoneNumber());
		tempStudent.setUpdatedOn(LocalDate.now());

		Card tempCard = tempStudent.getCard();
		tempCard.setEmail(studentPostDto.getEmail());
		tempCard.setUpdatedOn(LocalDate.now());

		tempStudent.setCard(tempCard);

		studentRepository.save(tempStudent);
		return modelMapper.map(tempStudent, StudentDto.class);

	}

	@Override
	public void deleteStudentById(UUID id) throws ResourceNotFoundException
	{
		Student tempStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Student id", id));
		studentRepository.delete(tempStudent);
	}
}
