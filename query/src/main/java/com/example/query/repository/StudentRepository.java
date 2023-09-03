package com.example.query.repository;

import com.example.query.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student save(Student student);
    Optional<Student> findById(Long id);
    List<Student> findAll();
    void deleteById(Long id);
}
