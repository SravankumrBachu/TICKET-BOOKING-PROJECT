package com.admission.service;

import com.admission.entity.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service class for Email notifications
 * Sends email notifications on application status changes
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    /**
     * Send status change notification email
     */
    public void sendStatusChangeNotification(Application application) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(application.getEmail());
            message.setSubject("Admission Application Status Update");

            String content = buildEmailContent(application);
            message.setText(content);

            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            // Don't throw exception to prevent application flow from breaking
        }
    }

    /**
     * Build email content based on application status
     */
    private String buildEmailContent(Application application) {
        StringBuilder content = new StringBuilder();
        content.append("Dear ").append(application.getApplicantName()).append(",\n\n");
        content.append("This is to inform you about the status of your admission application.\n\n");

        switch (application.getStatus()) {
            case SELECTED:
                content.append("Status: SELECTED\n");
                content.append("Congratulations! Your application for the course '")
                        .append(application.getCourse().getCourseName())
                        .append("' has been accepted.\n");
                content.append("Please contact the admission office for further details.\n");
                break;

            case REJECTED:
                content.append("Status: REJECTED\n");
                content.append("Unfortunately, your application for the course '")
                        .append(application.getCourse().getCourseName())
                        .append("' has been rejected.\n");
                if (application.getRejectionReason() != null) {
                    content.append("Reason: ").append(application.getRejectionReason()).append("\n");
                }
                break;

            case PENDING:
                content.append("Status: PENDING\n");
                content.append("Your application is currently under review. We will notify you soon.\n");
                break;
        }

        content.append("\nBest regards,\nAdmission Team");
        return content.toString();
    }
}
