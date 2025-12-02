package com.admission.service;

import com.admission.dto.CourseDTO;
import com.admission.entity.Course;
import com.admission.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Course management
 * Handles business logic for course operations
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    /**
     * Get all active courses
     */
    public List<CourseDTO> getAllActiveCourses() {
        return courseRepository.findByActive(true)
                .stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all courses
     */
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get course by ID
     */
    public CourseDTO getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        return modelMapper.map(course, CourseDTO.class);
    }

    /**
     * Create a new course
     */
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseDTO.class);
    }

    /**
     * Update an existing course
     */
    public CourseDTO updateCourse(Long courseId, CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        course.setCourseName(courseDTO.getCourseName());
        course.setDescription(courseDTO.getDescription());
        course.setDuration(courseDTO.getDuration());
        course.setLevel(courseDTO.getLevel());
        course.setActive(courseDTO.getActive());

        Course updatedCourse = courseRepository.save(course);
        return modelMapper.map(updatedCourse, CourseDTO.class);
    }

    /**
     * Delete a course
     */
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    /**
     * Get courses by level
     */
    public List<CourseDTO> getCoursesByLevel(String level) {
        return courseRepository.findByLevel(level)
                .stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }
}
