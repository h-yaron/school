package com.broadcom.school.service;

import com.broadcom.school.data.GradeRepository;
import com.broadcom.school.model.Course;
import com.broadcom.school.model.Grade;
import com.broadcom.school.model.GradeEntity;
import com.broadcom.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    private StudentService studentService;
    private CourseService courseService;
    private GradeRepository repository;

    @Autowired
    public GradeService(GradeRepository repository,
                        StudentService studentService,
                        CourseService courseService) {
        this.repository = repository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public List<GradeEntity> getAllGrades() {
        List<GradeEntity> result = new ArrayList<>();
        repository.findAll().forEach(grade -> result.add(grade));

        return result;
    }

    public GradeEntity getGrade(Long id) {
        Optional<GradeEntity> Grade = repository.findById(id);
        if (Grade.isPresent()) {
            return Grade.get();
        }
        throw new RuntimeException("Id not found: " + id);
    }

    public void createGrade(Grade grade) {
        Student student = studentService.getStudent(grade.getStudentId());
        Course course = courseService.getCourse(grade.getCourseId());

        GradeEntity gradeEntity = new GradeEntity();
        gradeEntity.setStudent(student);
        gradeEntity.setCourse(course);
        gradeEntity.setGrade(grade.getGrade());
        repository.save(gradeEntity);
    }

    public void updateGrade(Long id, GradeEntity Grade) {
        if (repository.existsById(id)) {
            repository.save(Grade);
        } else {
            throw new IllegalArgumentException("Grade id: " + id + " not found");
        }
    }

    public void deleteGrade(Long id) {
        repository.deleteById(id);
    }
}
