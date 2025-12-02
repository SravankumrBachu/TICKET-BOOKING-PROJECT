package com.admission.controller;

import com.admission.dto.CourseDTO;
import com.admission.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Course Management
 * Handles HTTP requests for course-related operations
 */
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    private final CourseService courseService;

    /**
     * GET all active courses
     */
    @GetMapping("/active")
    public ResponseEntity<List<CourseDTO>> getAllActiveCourses() {
        return ResponseEntity.ok(courseService.getAllActiveCourses());
    }

    /**
     * GET all courses
     */
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    /**
     * GET course by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    /**
     * POST create new course
     */
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseDTO));
    }

    /**
     * PUT update existing course
     */
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
    }

    /**
     * DELETE course
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET courses by level
     */
    @GetMapping("/level/{level}")
    public ResponseEntity<List<CourseDTO>> getCoursesByLevel(@PathVariable String level) {
        return ResponseEntity.ok(courseService.getCoursesByLevel(level));
    }
}
