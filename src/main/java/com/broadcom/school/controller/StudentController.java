package com.broadcom.school.controller;


import com.broadcom.school.model.Student;
import com.broadcom.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student get(@PathVariable String id) {
        return studentService.getStudent(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody Student student) {
        studentService.createStudent(student);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public void update(@PathVariable String id,
            @RequestBody Student student) {
        studentService.updateStudent(id, student);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        studentService.deleteStudent(id);
    }


}
