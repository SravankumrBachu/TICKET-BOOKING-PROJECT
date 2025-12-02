package com.admission.dto;

import com.admission.entity.Application.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Application (Request)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationRequestDTO {

    @NotBlank(message = "Applicant name is required")
    private String applicantName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    private String additionalInformation;

    @NotBlank(message = "Course ID is required")
    private Long courseId;
}
