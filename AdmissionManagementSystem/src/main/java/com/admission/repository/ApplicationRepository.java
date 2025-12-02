package com.admission.repository;

import com.admission.entity.Application;
import com.admission.entity.Application.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Application entity operations
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    /**
     * Find all applications by status
     */
    List<Application> findByStatus(ApplicationStatus status);

    /**
     * Find application by email
     */
    Optional<Application> findByEmail(String email);

    /**
     * Find all applications for a specific course
     */
    List<Application> findByCourseId(Long courseId);

    /**
     * Find all applications by course and status
     */
    List<Application> findByCourseIdAndStatus(Long courseId, ApplicationStatus status);

    /**
     * Find all selected applications (students)
     */
    @Query("SELECT a FROM Application a WHERE a.status = 'SELECTED' ORDER BY a.statusChangedAt DESC")
    List<Application> findAllSelectedApplications();

    /**
     * Count applications by status
     */
    Long countByStatus(ApplicationStatus status);

    /**
     * Find all pending applications ordered by creation date
     */
    List<Application> findByStatusOrderByCreatedAtAsc(ApplicationStatus status);
}
