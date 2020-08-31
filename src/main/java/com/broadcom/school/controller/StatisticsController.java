package com.broadcom.school.controller;

import com.broadcom.school.model.Course;
import com.broadcom.school.model.Student;
import com.broadcom.school.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping(value = "/bestStudent")
    public Student getBest() {
        return statisticsService.getBestStudent();
    }

    @RequestMapping(value = "/easyCourse")
    public Course getEasy() {
        return statisticsService.getEasyCourse();
    }
}
