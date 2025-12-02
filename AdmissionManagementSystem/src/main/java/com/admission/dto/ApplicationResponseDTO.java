package com.admission.dto;

import com.admission.entity.Application.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Application (Response)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponseDTO {
    private Long id;

    private String applicantName;

    private String email;

    private String phoneNumber;

    private String address;

    private String additionalInformation;

    private ApplicationStatus status;

    private String rejectionReason;

    private CourseDTO course;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime statusChangedAt;
}
