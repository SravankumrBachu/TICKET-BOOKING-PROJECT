package com.admission.controller;

import com.admission.dto.ApplicationRequestDTO;
import com.admission.dto.ApplicationResponseDTO;
import com.admission.dto.ApplicationStatusUpdateDTO;
import com.admission.entity.Student;
import com.admission.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Application Management
 * Handles HTTP requests for student applications and admin dashboard operations
 */
@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ApplicationController {

    private final ApplicationService applicationService;

    /**
     * POST submit new application (public endpoint)
     */
    @PostMapping("/submit")
    public ResponseEntity<ApplicationResponseDTO> submitApplication(@RequestBody ApplicationRequestDTO requestDTO) {
        ApplicationResponseDTO response = applicationService.submitApplication(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET all applications (admin only)
     */
    @GetMapping
    public ResponseEntity<List<ApplicationResponseDTO>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    /**
     * GET application by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    /**
     * GET all pending applications
     */
    @GetMapping("/status/pending")
    public ResponseEntity<List<ApplicationResponseDTO>> getPendingApplications() {
        return ResponseEntity.ok(applicationService.getPendingApplications());
    }

    /**
     * GET all selected applications (Students Report)
     */
    @GetMapping("/status/selected")
    public ResponseEntity<List<ApplicationResponseDTO>> getSelectedApplications() {
        return ResponseEntity.ok(applicationService.getSelectedApplications());
    }

    /**
     * PUT update application status (admin only)
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationResponseDTO> updateApplicationStatus(
            @PathVariable Long id,
            @RequestBody ApplicationStatusUpdateDTO updateDTO) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(id, updateDTO));
    }

    /**
     * GET all selected students (automated report)
     */
    @GetMapping("/students/all")
    public ResponseEntity<List<Student>> getAllSelectedStudents() {
        return ResponseEntity.ok(applicationService.getAllSelectedStudents());
    }

    /**
     * GET students by course
     */
    @GetMapping("/students/course/{courseId}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(applicationService.getStudentsByCourse(courseId));
    }

    /**
     * GET application statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<ApplicationService.ApplicationStatisticsDTO> getStatistics() {
        return ResponseEntity.ok(applicationService.getApplicationStatistics());
    }
}
