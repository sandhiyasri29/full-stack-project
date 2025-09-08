package com.example.coursereviews.repo;

import com.example.coursereviews.model.Review;
import com.example.coursereviews.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCourse(Course course);
    @Query("""
        select avg(r.rating) from Review r where r.course.id = :courseId
    """)
    Optional<Double> averageRating(@Param("courseId") Long courseId);

    @Query("""
        select count(r) from Review r where r.course.id = :courseId
    """)
    long countByCourseId(@Param("courseId") Long courseId);
}
