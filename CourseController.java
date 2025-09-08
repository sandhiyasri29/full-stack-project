package com.example.coursereviews.web;

import com.example.coursereviews.dto.CourseRequest;
import com.example.coursereviews.model.Course;
import com.example.coursereviews.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> list(@RequestParam(required = false) String search,
                             @RequestParam(required = false) String category) {
        return courseService.listCourses(search, category);
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @PostMapping
    public ResponseEntity<Course> create(@Validated @RequestBody CourseRequest req) {
        return ResponseEntity.ok(courseService.createCourse(req));
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @Validated @RequestBody CourseRequest req) {
        return courseService.updateCourse(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/rating")
    public ResponseEntity<RatingResponse> rating(@PathVariable Long id) {
        double avg = courseService.averageRating(id);
        long count = courseService.reviewCount(id);
        return ResponseEntity.ok(new RatingResponse(avg, count));
    }

    public record RatingResponse(double average, long count) {}
}
