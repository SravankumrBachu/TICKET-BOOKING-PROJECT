package com.admission.repository;

import com.admission.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Student entity operations
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    /**
     * Find student by email
     */
    Optional<Student> findByEmail(String email);

    /**
     * Find all students in a course
     */
    List<Student> findByCourseId(Long courseId);

    /**
     * Find student by application ID
     */
    Optional<Student> findByApplicationId(Long applicationId);

    /**
     * Find all active students
     */
    List<Student> findByEnrollmentStatus(String enrollmentStatus);
}
