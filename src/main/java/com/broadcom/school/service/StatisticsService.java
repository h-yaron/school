package com.broadcom.school.service;

import com.broadcom.school.model.Course;
import com.broadcom.school.model.GradeEntity;
import com.broadcom.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class StatisticsService {

    private GradeService gradeService;

    @Autowired
    public StatisticsService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public Student getBestStudent() {
        return getBest(grade-> grade.getStudent());
    }

    public Course getEasyCourse() {
        return getBest(grade-> grade.getCourse());
    }

    private <R> R getBest(Function<GradeEntity,R> off) {
        List<GradeEntity> grades = gradeService.getAllGrades();

        HashMap<R,List<Integer>> map = new HashMap<>();

        grades.stream().forEach(grade -> addGrade(map, grade, off));

        double bestGrade = -1;
        R best = null;
        for (Map.Entry<R,List<Integer>> entry : map.entrySet()) {
            double sum = 0;
            for (Integer grade : entry.getValue()) {
                sum += grade;
            }
            double average = sum / entry.getValue().size();
            if (average > bestGrade) {
                bestGrade = average;
                best = entry.getKey();
            }
        }
        return best;
    }

    private <T> void addGrade(HashMap<T, List<Integer>> map, GradeEntity grade, Function<GradeEntity,T> off) {
        List<Integer> grades = map.get(grade.getStudent());
        if (grades == null) {
            grades = new ArrayList<>();
            map.put(off.apply(grade), grades);
        }
        grades.add(grade.getGrade());
    }

}
