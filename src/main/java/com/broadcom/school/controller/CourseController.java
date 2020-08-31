package com.broadcom.school.controller;


import com.broadcom.school.model.Course;
import com.broadcom.school.model.Student;
import com.broadcom.school.service.CourseService;
import com.broadcom.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private StudentService studentService;
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService curseService,
                            StudentService studentService) {
        this.courseService = curseService;
        this.studentService = studentService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Course> getAll() {
        return courseService.getAllCourses();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Course get(@PathVariable Integer id) {
        return courseService.getCourse(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody Course course) {
        courseService.createCourse(course);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public void update(@PathVariable Integer id,
            @RequestBody Course course) {
        courseService.updateCourse(id, course);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }

    @RequestMapping(value = "/{id}/enroll/{student}", method = RequestMethod.POST)
    public void enroll(@PathVariable Integer id,@PathVariable String student) {
        courseService.enroll(courseService.getCourse(id), studentService.getStudent(student));
    }


}
