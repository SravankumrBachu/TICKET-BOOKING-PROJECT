package com.admission.dto;

import com.admission.entity.Application.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for updating Application Status
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStatusUpdateDTO {

    @NotBlank(message = "Status is required")
    private ApplicationStatus status;

    private String rejectionReason; // Required if status is REJECTED
}
