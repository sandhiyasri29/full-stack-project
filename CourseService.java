package com.example.coursereviews.service;

import com.example.coursereviews.dto.CourseRequest;
import com.example.coursereviews.dto.ReviewRequest;
import com.example.coursereviews.model.Course;
import com.example.coursereviews.model.Review;
import com.example.coursereviews.repo.CourseRepository;
import com.example.coursereviews.repo.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final ReviewRepository reviewRepository;

    public CourseService(CourseRepository courseRepository, ReviewRepository reviewRepository) {
        this.courseRepository = courseRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Course> listCourses(String search, String category) {
        if (search != null && !search.isBlank()) {
            return courseRepository.findByTitleContainingIgnoreCaseOrInstructorContainingIgnoreCase(search, search);
        }
        if (category != null && !category.isBlank()) {
            return courseRepository.findByCategoryIgnoreCase(category);
        }
        return courseRepository.findAll();
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course createCourse(CourseRequest req) {
        Course c = new Course(req.title(), req.instructor(), req.category(), req.description());
        return courseRepository.save(c);
    }

    public Course updateCourse(Long id, CourseRequest req) {
        Course c = getCourse(id);
        c.setTitle(req.title());
        c.setInstructor(req.instructor());
        c.setCategory(req.category());
        c.setDescription(req.description());
        return courseRepository.save(c);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Review addReview(Long courseId, ReviewRequest req) {
        Course c = getCourse(courseId);
        Review r = new Review(c, req.rating(), req.reviewerName(), req.comment());
        return reviewRepository.save(r);
    }

    public List<Review> listReviews(Long courseId) {
        Course c = getCourse(courseId);
        return reviewRepository.findByCourse(c);
    }

    public double averageRating(Long courseId) {
        return reviewRepository.averageRating(courseId).orElse(0.0);
    }

    public long reviewCount(Long courseId) {
        return reviewRepository.countByCourseId(courseId);
    }
}
