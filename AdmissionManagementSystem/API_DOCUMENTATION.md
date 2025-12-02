# API Documentation

## Base URL
```
http://localhost:8080/api
```

## Endpoints Overview

### Course Management API

#### Get All Courses
```http
GET /courses
```
**Response**: List of all courses
```json
[
  {
    "id": 1,
    "courseName": "Java Advanced",
    "description": "Advanced Java Programming",
    "duration": 3,
    "level": "Advanced",
    "active": true,
    "createdAt": "2025-12-02T10:30:00",
    "updatedAt": "2025-12-02T10:30:00"
  }
]
```

#### Get Active Courses
```http
GET /courses/active
```
**Response**: Only active courses

#### Get Course by ID
```http
GET /courses/{id}
```
**Path Parameter**: `id` - Course ID

#### Create Course (Admin)
```http
POST /courses
Content-Type: application/json

{
  "courseName": "Python Basics",
  "description": "Learn Python programming",
  "duration": 2,
  "level": "Beginner",
  "active": true
}
```
**Response**: `201 Created` with created course object

#### Update Course (Admin)
```http
PUT /courses/{id}
Content-Type: application/json

{
  "courseName": "Python Advanced",
  "description": "Advanced Python",
  "duration": 3,
  "level": "Advanced",
  "active": true
}
```

#### Delete Course (Admin)
```http
DELETE /courses/{id}
```
**Response**: `204 No Content`

#### Get Courses by Level
```http
GET /courses/level/{level}
```
**Path Parameter**: `level` - "Beginner", "Intermediate", or "Advanced"

---

### Application Management API

#### Submit Application (Public)
```http
POST /applications/submit
Content-Type: application/json

{
  "applicantName": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "address": "123 Main Street, City",
  "additionalInformation": "Experienced in Java",
  "courseId": 1
}
```
**Response**: `201 Created`
```json
{
  "id": 1,
  "applicantName": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "address": "123 Main Street, City",
  "additionalInformation": "Experienced in Java",
  "status": "PENDING",
  "rejectionReason": null,
  "course": {...},
  "createdAt": "2025-12-02T10:30:00",
  "updatedAt": "2025-12-02T10:30:00",
  "statusChangedAt": null
}
```

**Validation Rules**:
- Email must be unique
- Phone number must be exactly 10 digits
- All required fields must be filled
- Course must exist

#### Get All Applications (Admin)
```http
GET /applications
```
**Response**: List of all applications with all statuses

#### Get Application by ID
```http
GET /applications/{id}
```

#### Get Pending Applications (Admin)
```http
GET /applications/status/pending
```
**Response**: Only PENDING applications ordered by creation date

#### Get Selected Applications (Students Report)
```http
GET /applications/status/selected
```
**Response**: Only SELECTED applications (automatically updated when status changes)

#### Update Application Status (Admin)
```http
PUT /applications/{id}/status
Content-Type: application/json

{
  "status": "SELECTED"
}
```
**Response**: Updated application object

For rejection:
```json
{
  "status": "REJECTED",
  "rejectionReason": "Does not meet prerequisites"
}
```

**Rules**:
- Status must be one of: PENDING, SELECTED, REJECTED
- Rejection reason is required when status is REJECTED
- When changed to SELECTED, automatic Student record is created
- Email notification is sent to applicant

---

### Student Management API

#### Get All Enrolled Students
```http
GET /applications/students/all
```
**Response**: List of all students (created from SELECTED applications)
```json
[
  {
    "id": 1,
    "studentName": "John Doe",
    "email": "john@example.com",
    "phoneNumber": "9876543210",
    "address": "123 Main Street",
    "course": {...},
    "applicationId": 1,
    "enrollmentStatus": "ACTIVE",
    "createdAt": "2025-12-02T10:30:00",
    "updatedAt": "2025-12-02T10:30:00"
  }
]
```

#### Get Students by Course
```http
GET /applications/students/course/{courseId}
```
**Response**: Students enrolled in specific course

---

### Statistics API

#### Get Application Statistics
```http
GET /applications/statistics
```
**Response**:
```json
{
  "totalApplications": 100,
  "pendingApplications": 30,
  "selectedApplications": 60,
  "rejectedApplications": 10
}
```

---

## Response Codes

| Code | Meaning | Description |
|------|---------|-------------|
| 200 | OK | Successful GET request |
| 201 | Created | Successful POST request |
| 204 | No Content | Successful DELETE request |
| 400 | Bad Request | Invalid request data |
| 404 | Not Found | Resource not found |
| 409 | Conflict | Duplicate email or other conflict |
| 500 | Server Error | Internal server error |

---

## Error Response Format

```json
{
  "timestamp": "2025-12-02T10:30:00.123456",
  "status": 400,
  "error": "Bad Request",
  "message": "Email already registered with an application",
  "path": "/api/applications/submit"
}
```

---

## Authentication Notes

Currently, all endpoints are public. For production, implement:
- JWT authentication for admin endpoints
- Role-based access control
- Session management
- API key validation

---

## CORS Configuration

Currently enabled for all origins. Update in `ApplicationController` for production:
```java
@CrossOrigin(origins = "https://your-frontend-domain.com", maxAge = 3600)
```

---

## Rate Limiting (Future)

Consider implementing rate limiting for production:
- Applications submission: 10 per hour per IP
- Admin endpoints: 100 per hour per user
- Public endpoints: 1000 per hour per IP

---

## Data Export (Future)

Consider adding endpoints for:
- Export applications to CSV
- Export reports to PDF
- Batch operations

---

## Webhooks (Future)

Consider implementing webhooks for:
- Application status changes
- New applications submitted
- Enrollment updates

---

For implementation examples and testing, refer to [BUILD_GUIDE.md](BUILD_GUIDE.md)
