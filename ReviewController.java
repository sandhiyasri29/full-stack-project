package com.example.coursereviews.web;

import com.example.coursereviews.dto.ReviewRequest;
import com.example.coursereviews.model.Review;
import com.example.coursereviews.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReviewController {

    private final CourseService courseService;

    public ReviewController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses/{courseId}/reviews")
    public List<Review> list(@PathVariable Long courseId) {
        return courseService.listReviews(courseId);
    }

    @PostMapping("/courses/{courseId}/reviews")
    public ResponseEntity<Review> add(@PathVariable Long courseId,
                                      @Validated @RequestBody ReviewRequest req) {
        return ResponseEntity.ok(courseService.addReview(courseId, req));
    }
}
