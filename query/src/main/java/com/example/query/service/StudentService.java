package com.example.query.service;

import com.example.query.model.Student;
import com.example.query.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create (Save) a student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Read (Find) a student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Read (Find) all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Update a student
    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);

        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setAge(updatedStudent.getAge());
            existingStudent.setMajor(updatedStudent.getMajor());

            return studentRepository.save(existingStudent);
        } else {
            // Handle the case where the student with the given ID doesn't exist.
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }
    }

    // Delete a student by ID
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
