package com.admission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Email Notification
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailNotificationDTO {
    private String recipientEmail;

    private String applicantName;

    private String courseName;

    private String status;

    private String rejectionReason;

    private String message;
}
