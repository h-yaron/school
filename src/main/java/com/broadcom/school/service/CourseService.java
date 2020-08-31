package com.broadcom.school.service;

import com.broadcom.school.data.CourseRepository;
import com.broadcom.school.model.Course;
import com.broadcom.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private StudentService studentService;
    private CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAllCourses() {
        List<Course> result = new ArrayList<>();
        repository.findAll().forEach(course -> result.add(course));

        return result;
    }

    public Course getCourse(Integer id) {
        Optional<Course> course = repository.findById(id);
        if (course.isPresent()) {
            return course.get();
        }
        throw new RuntimeException("Id not found: " + id);
    }

    public void createCourse(Course course) {
        if (!repository.existsById(course.getId())) {
            repository.save(course);
        } else {
            throw new IllegalArgumentException("Course id already in exists");
        }
    }

    public void updateCourse(Integer id, Course student) {
        if (repository.existsById(id)) {
            repository.save(student);
        } else {
            throw new IllegalArgumentException("Course id: " + id + " not found");
        }
    }

    public void deleteCourse(Integer id) {
        repository.deleteById(id);
    }

    public void enroll(Course course, Student student) {
        course.getEnrolledStudents().add(student);
        repository.save(course);
    }
}
