package com.admission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Data Transfer Object for Course
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private Long id;

    @NotBlank(message = "Course name cannot be blank")
    private String courseName;

    private String description;

    @NotBlank(message = "Duration cannot be blank")
    private Integer duration;

    @NotBlank(message = "Level cannot be blank")
    private String level;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
