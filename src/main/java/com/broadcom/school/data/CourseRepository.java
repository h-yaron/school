package com.broadcom.school.data;

import com.broadcom.school.model.Course;
import com.broadcom.school.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
}
