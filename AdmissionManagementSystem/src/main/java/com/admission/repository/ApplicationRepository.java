package com.admission.repository;

import com.admission.entity.Application;
import com.admission.entity.Application.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
   
    List<Application> findByStatus(ApplicationStatus status);
    Optional<Application> findByEmail(String email);
    List<Application> findByCourseId(Long courseId);
    List<Application> findByCourseIdAndStatus(Long courseId, ApplicationStatus status);
    @Query("SELECT a FROM Application a WHERE a.status = 'SELECTED' ORDER BY a.statusChangedAt DESC")
    List<Application> findAllSelectedApplications();
    Long countByStatus(ApplicationStatus status);
    List<Application> findByStatusOrderByCreatedAtAsc(ApplicationStatus status);
}
