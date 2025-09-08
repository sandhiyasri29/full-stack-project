package com.example.coursereviews.repo;

import com.example.coursereviews.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTitleContainingIgnoreCaseOrInstructorContainingIgnoreCase(String title, String instructor);
    List<Course> findByCategoryIgnoreCase(String category);
}
