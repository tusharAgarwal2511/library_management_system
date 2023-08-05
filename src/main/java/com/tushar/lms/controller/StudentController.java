package com.tushar.lms.controller;

import com.tushar.lms.exceptions.ResourceNotFoundException;
import com.tushar.lms.payloads.ApiResponse;
import com.tushar.lms.payloads.StudentDto;
import com.tushar.lms.payloads.StudentPostDto;
import com.tushar.lms.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student/")
public class StudentController
{
	@Autowired
	private StudentService studentService;

	@PostMapping("/")
	public ResponseEntity<StudentDto> createStudentDto(@RequestBody StudentPostDto studentPostDto)
	{
		StudentDto tempStudent = studentService.createStudent(studentPostDto);
		return new ResponseEntity<StudentDto>(tempStudent, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") UUID id) throws ResourceNotFoundException
	{
		StudentDto tempStudent = studentService.getStudentById(id);
		return new ResponseEntity<StudentDto>(tempStudent, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<StudentDto>> getAllStudents()
	{
		List<StudentDto> studentDtos = studentService.getAllStudents();
		return new ResponseEntity<List<StudentDto>>(studentDtos, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentDto> updateStudentById(@RequestBody StudentPostDto studentPostDto, @PathVariable("id") UUID id) throws ResourceNotFoundException
	{
		StudentDto tempStudent = studentService.updateStudentById(studentPostDto, id);
		return new ResponseEntity<StudentDto>(tempStudent, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteStudentById(@PathVariable UUID id) throws ResourceNotFoundException
	{
		studentService.deleteStudentById(id);
		ApiResponse deleteApiResponse = new ApiResponse();
		deleteApiResponse.setSuccess(true);
		deleteApiResponse.setMessage("Student Deleted Successfully");
		return new ResponseEntity<ApiResponse>(deleteApiResponse, HttpStatus.OK);
	}
}
