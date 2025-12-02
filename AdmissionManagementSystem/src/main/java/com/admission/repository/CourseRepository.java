package com.admission.repository;

import com.admission.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Course entity operations
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * Find all active courses
     */
    List<Course> findByActive(Boolean active);

    /**
     * Find a course by name
     */
    Optional<Course> findByCourseName(String courseName);

    /**
     * Find courses by level
     */
    List<Course> findByLevel(String level);
}
