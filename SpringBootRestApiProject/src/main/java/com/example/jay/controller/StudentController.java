package com.example.jay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jay.Entity.Student;
import com.example.jay.Repository.StudentRepo;

@RestController

public class StudentController {
//localhost:9191/students
	@Autowired
	StudentRepo repo;
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		 List<Student> students = repo.findAll();
		return students;
	}
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		return student;
	}
	
	@PostMapping("/students/add")
	public void createStudent(@RequestBody Student student) {
		repo.save(student);
	
		
	}
	 @PutMapping("/{id}")
	    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody  Student updatedStudent ) {
		 return repo.findById(id).map(existingStudent ->{
			 
			 existingStudent.setName(updatedStudent.getName());
			 existingStudent.setPercentage(updatedStudent.getPercentage());
			 existingStudent.setBranch(updatedStudent.getBranch());;
			
		 
			 Student savedStudent = repo.save(existingStudent);
			// return savedStudent;
			  return new ResponseEntity<>(savedStudent, HttpStatus.OK);
		      
		 })
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
				 

	          
	     
	    }
	 
//	 public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
//	        return studentRepository.findById(id)
//	                .map(existingStudent -> {
//	                    existingStudent.setName(updatedStudent.getName());
//	                    existingStudent.setAge(updatedStudent.getAge());
//	                    // Set other fields as needed
//
//	                    Student savedStudent = studentRepository.save(existingStudent);
//	                    return new ResponseEntity<>(savedStudent, HttpStatus.OK);
//	                })
//	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	    }
	
}
