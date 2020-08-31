package com.broadcom.school.controller;


import com.broadcom.school.model.Grade;
import com.broadcom.school.model.GradeEntity;
import com.broadcom.school.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<GradeEntity> getAll() {
        return gradeService.getAllGrades();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GradeEntity get(@PathVariable Long id) {
        return gradeService.getGrade(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody Grade Grade) {
        gradeService.createGrade(Grade);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public void update(@PathVariable Long id,
            @RequestBody GradeEntity Grade) {
        gradeService.updateGrade(id, Grade);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        gradeService.deleteGrade(id);
    }


}
