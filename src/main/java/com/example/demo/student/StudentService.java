package com.example.demo.student;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}

	public void deletStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("student with id " + studentId + " does not exists");
		}
		studentRepository.deleteById(studentId);
	}
	
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> 
			new IllegalStateException("student with id " + studentId + " does not exist")
				);
		
		// Update Name
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		// Update Email
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("Email taken");
			}
			student.setEmail(email);
		}
		
	}

	public Optional<Student> studentById(Long studentId) {
		
		Optional<Student> studentOptional = studentRepository.findStudentById(studentId);
		if(studentOptional.isEmpty()) {
			throw new IllegalStateException("student with id " + studentId + " does not exists");
		}
		
		return studentRepository.findById(studentId);
		
	}
	
}
