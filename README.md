# Online Course Review Platform (Spring Boot)

A minimal REST API for managing courses and course reviews.

## Features
- CRUD courses
- Add/list reviews for a course
- Average rating + review count per course
- Search by title/instructor or filter by category
- H2 in-memory DB, H2 Console enabled
- CORS enabled for quick frontend hookup

## Run
```bash
mvn spring-boot:run
```

## API Quickstart
- List courses: `GET /api/courses`
- Search by title/instructor: `GET /api/courses?search=java`
- Filter by category: `GET /api/courses?category=Programming`
- Get one: `GET /api/courses/{id}`
- Create: `POST /api/courses` body:
```json
{ "title": "Spring Boot 101", "instructor": "Jane Doe", "category": "Programming", "description":"..." }
```
- Update: `PUT /api/courses/{id}` (same body as create)
- Delete: `DELETE /api/courses/{id}`
- Add review: `POST /api/courses/{id}/reviews` body:
```json
{ "rating": 5, "reviewerName": "Sandhiya", "comment":"Great!" }
```
- List reviews: `GET /api/courses/{id}/reviews`
- Course rating summary: `GET /api/courses/{id}/rating` -> `{ "average": 4.5, "count": 12 }`

## Notes
- Uses Java 17+. Update `pom.xml` if needed.
- Switch to a real DB by changing `spring.datasource.*` in `application.properties`.
