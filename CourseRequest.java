package com.example.coursereviews.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CourseRequest(
        @NotBlank @Size(max = 120) String title,
        @NotBlank @Size(max = 80) String instructor,
        @Size(max = 60) String category,
        @Size(max = 2000) String description
) {}
