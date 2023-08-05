package com.tushar.lms.service.student;

import com.tushar.lms.exceptions.ResourceNotFoundException;
import com.tushar.lms.payloads.StudentDto;
import com.tushar.lms.payloads.StudentPostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface StudentService
{
	StudentDto createStudent(StudentPostDto studentPostDto);

	StudentDto getStudentById(UUID id) throws ResourceNotFoundException;

	List<StudentDto> getAllStudents();

	StudentDto updateStudentById(StudentPostDto studentDto, UUID id) throws ResourceNotFoundException;

	void deleteStudentById(UUID id) throws ResourceNotFoundException;

}
