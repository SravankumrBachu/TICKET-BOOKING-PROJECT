package com.admission.service;

import com.admission.dto.ApplicationRequestDTO;
import com.admission.dto.ApplicationResponseDTO;
import com.admission.dto.ApplicationStatusUpdateDTO;
import com.admission.entity.Application;
import com.admission.entity.Application.ApplicationStatus;
import com.admission.entity.Course;
import com.admission.entity.Student;
import com.admission.repository.ApplicationRepository;
import com.admission.repository.CourseRepository;
import com.admission.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Application management
 * Handles business logic for application processing and status updates
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    /**
     * Submit a new application
     */
    public ApplicationResponseDTO submitApplication(ApplicationRequestDTO requestDTO) {
        // Check if email already exists
        if (applicationRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered with an application");
        }

        // Get course
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + requestDTO.getCourseId()));

        // Create application
        Application application = Application.builder()
                .applicantName(requestDTO.getApplicantName())
                .email(requestDTO.getEmail())
                .phoneNumber(requestDTO.getPhoneNumber())
                .address(requestDTO.getAddress())
                .additionalInformation(requestDTO.getAdditionalInformation())
                .course(course)
                .status(ApplicationStatus.PENDING)
                .build();

        Application savedApplication = applicationRepository.save(application);
        return modelMapper.map(savedApplication, ApplicationResponseDTO.class);
    }

    /**
     * Get all applications
     */
    public List<ApplicationResponseDTO> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(app -> modelMapper.map(app, ApplicationResponseDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get application by ID
     */
    public ApplicationResponseDTO getApplicationById(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + applicationId));
        return modelMapper.map(application, ApplicationResponseDTO.class);
    }

    /**
     * Get all pending applications
     */
    public List<ApplicationResponseDTO> getPendingApplications() {
        return applicationRepository.findByStatusOrderByCreatedAtAsc(ApplicationStatus.PENDING)
                .stream()
                .map(app -> modelMapper.map(app, ApplicationResponseDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all selected applications (students)
     */
    public List<ApplicationResponseDTO> getSelectedApplications() {
        return applicationRepository.findAllSelectedApplications()
                .stream()
                .map(app -> modelMapper.map(app, ApplicationResponseDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Update application status
     * When status is SELECTED, create a Student record
     */
    public ApplicationResponseDTO updateApplicationStatus(Long applicationId, ApplicationStatusUpdateDTO updateDTO) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + applicationId));

        ApplicationStatus previousStatus = application.getStatus();
        application.setStatus(updateDTO.getStatus());
        application.setStatusChangedAt(LocalDateTime.now());

        // If status is REJECTED, set the rejection reason
        if (updateDTO.getStatus() == ApplicationStatus.REJECTED) {
            if (updateDTO.getRejectionReason() == null || updateDTO.getRejectionReason().trim().isEmpty()) {
                throw new RuntimeException("Rejection reason is required when rejecting an application");
            }
            application.setRejectionReason(updateDTO.getRejectionReason());
        }

        Application updatedApplication = applicationRepository.save(application);

        // If status changed to SELECTED, create a Student record
        if (updateDTO.getStatus() == ApplicationStatus.SELECTED && previousStatus != ApplicationStatus.SELECTED) {
            createStudentFromApplication(updatedApplication);
        }

        // Send email notification
        try {
            emailService.sendStatusChangeNotification(updatedApplication);
        } catch (Exception e) {
            // Log but don't fail the operation if email fails
            System.err.println("Failed to send email notification: " + e.getMessage());
        }

        return modelMapper.map(updatedApplication, ApplicationResponseDTO.class);
    }

    /**
     * Create a Student record from an accepted Application
     */
    private void createStudentFromApplication(Application application) {
        // Check if student already exists
        if (studentRepository.findByApplicationId(application.getId()).isPresent()) {
            return;
        }

        Student student = Student.builder()
                .studentName(application.getApplicantName())
                .email(application.getEmail())
                .phoneNumber(application.getPhoneNumber())
                .address(application.getAddress())
                .course(application.getCourse())
                .applicationId(application.getId())
                .enrollmentStatus("ACTIVE")
                .build();

        studentRepository.save(student);
    }

    /**
     * Get all selected students
     */
    public List<Student> getAllSelectedStudents() {
        return studentRepository.findByEnrollmentStatus("ACTIVE");
    }

    /**
     * Get students by course
     */
    public List<Student> getStudentsByCourse(Long courseId) {
        return studentRepository.findByCourseId(courseId);
    }

    /**
     * Count applications by status
     */
    public Long countApplicationsByStatus(ApplicationStatus status) {
        return applicationRepository.countByStatus(status);
    }

    /**
     * Get applications statistics
     */
    public ApplicationStatisticsDTO getApplicationStatistics() {
        return ApplicationStatisticsDTO.builder()
                .totalApplications(applicationRepository.count())
                .pendingApplications(applicationRepository.countByStatus(ApplicationStatus.PENDING))
                .selectedApplications(applicationRepository.countByStatus(ApplicationStatus.SELECTED))
                .rejectedApplications(applicationRepository.countByStatus(ApplicationStatus.REJECTED))
                .build();
    }

    // DTO for statistics
    @lombok.Data
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    @lombok.Builder
    public static class ApplicationStatisticsDTO {
        private Long totalApplications;
        private Long pendingApplications;
        private Long selectedApplications;
        private Long rejectedApplications;
    }
}
