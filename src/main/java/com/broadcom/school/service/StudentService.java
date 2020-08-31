package com.broadcom.school.service;

import com.broadcom.school.data.StudentRepository;
import com.broadcom.school.model.Student;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        repository.findAll().forEach(student -> result.add(student));

        return result;
    }

    public Student getStudent(String id) {
        Optional<Student> student = repository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        throw new RuntimeException("Id not found: " + id);
    }

    public void createStudent(Student student) {
        if (!repository.existsById(student.getId())) {
            repository.save(student);
        } else {
            throw new IllegalArgumentException("Student id already in exists");
        }
    }

    public void updateStudent(String id, Student student) {
        if (repository.existsById(id)) {
            repository.save(student);
        } else {
            throw new IllegalArgumentException("Student id: " + id + " not found");
        }
    }

    public void deleteStudent(String id) {
        repository.deleteById(id);
    }
}
