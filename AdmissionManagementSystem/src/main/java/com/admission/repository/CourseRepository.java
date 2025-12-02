package com.admission.repository;

import com.admission.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByActive(Boolean active);
    Optional<Course> findByCourseName(String courseName);
    List<Course> findByLevel(String level);
}
