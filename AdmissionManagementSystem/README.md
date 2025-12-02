# Admission Management System - README

## Project Overview

A comprehensive **Admission Management System** built with **Spring Boot**, designed to replicate Zoho Creator functionality. This application manages the complete admission workflow from application submission to student enrollment.

## Features

### 1. **Admission Form (Public Page)**
- Students can submit applications without login
- Form fields:
  - Applicant Name (Required)
  - Email (Required, Unique)
  - Phone Number (Required, 10 digits validation)
  - Course (Required, Dropdown from available courses)
  - Address (Required)
  - Additional Information (Optional)

### 2. **Course Management**
- View all available courses
- Create, update, and delete courses
- Filter courses by level (Beginner, Intermediate, Advanced)
- Course details include: name, description, duration, level, active status

### 3. **Admin Dashboard**
- **All Applications Report**: View all applications with search and filter
- **Selected Students Report**: Automatically shows only SELECTED applications
- **Application Statistics**: Total, Pending, Selected, Rejected counts

### 4. **Application Status Management**
- Status options: PENDING, SELECTED, REJECTED
- Update application status (Admin only)
- Capture rejection reason when rejecting applications
- Automatic email notifications on status change
- Automatic Student record creation when status is SELECTED

### 5. **Automation & Workflows**
- Status change triggers automatic Student record creation
- Email notifications on status updates
- Input validation on all forms
- Duplicate email prevention
- Cascading updates and filtering

### 6. **Student Management**
- Automatic student enrollment when application is SELECTED
- View enrolled students
- Filter students by course
- Track enrollment status

## Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Build Tool**: Maven
- **Database**: H2 (Development), MySQL (Production ready)
- **ORM**: Spring Data JPA + Hibernate
- **Mapping**: ModelMapper
- **Validation**: Spring Validation + Jakarta Bean Validation
- **Email**: Spring Mail
- **Lombok**: For reducing boilerplate code

## Project Structure

```
AdmissionManagementSystem/
├── src/
│   ├── main/
│   │   ├── java/com/admission/
│   │   │   ├── entity/          # JPA Entity classes
│   │   │   ├── repository/      # Spring Data JPA Repositories
│   │   │   ├── service/         # Business Logic Layer
│   │   │   ├── controller/      # REST API Controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── config/          # Spring Configuration
│   │   │   └── AdmissionManagementApplication.java  # Main App
│   │   └── resources/
│   │       └── application.properties  # Configuration
│   └── test/
│       └── java/               # Unit Tests
├── pom.xml                      # Maven Configuration
└── README.md                    # This file
```

## Database Schema

### Tables:
1. **courses** - Course information
2. **applications** - Student applications
3. **students** - Enrolled students

## API Endpoints

### Course Management
- `GET /api/courses` - Get all courses
- `GET /api/courses/active` - Get active courses
- `GET /api/courses/{id}` - Get course by ID
- `POST /api/courses` - Create course (Admin)
- `PUT /api/courses/{id}` - Update course (Admin)
- `DELETE /api/courses/{id}` - Delete course (Admin)
- `GET /api/courses/level/{level}` - Get courses by level

### Application Management
- `POST /api/applications/submit` - Submit application (Public)
- `GET /api/applications` - Get all applications (Admin)
- `GET /api/applications/{id}` - Get application by ID
- `GET /api/applications/status/pending` - Get pending applications
- `GET /api/applications/status/selected` - Get selected applications (Students Report)
- `PUT /api/applications/{id}/status` - Update application status (Admin)
- `GET /api/applications/statistics` - Get application statistics

### Student Management
- `GET /api/applications/students/all` - Get all enrolled students
- `GET /api/applications/students/course/{courseId}` - Get students by course

## Setup & Installation

### Prerequisites
- Java 17 or higher
- Maven 3.8+
- MySQL (Optional, for production)

### Development Setup

1. **Clone the repository**
   ```bash
   cd AdmissionManagementSystem
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Application URL: `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console`

### Configuration

Update `application.properties` for:
- Database connection
- Email SMTP settings
- Server port
- Logging levels

### Production Setup

For production deployment:
1. Change database to MySQL
2. Configure environment variables for sensitive data
3. Set proper email SMTP credentials
4. Enable security/authentication
5. Configure SSL/TLS

## Email Configuration

The system sends email notifications on application status changes. Configure SMTP settings:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Usage Workflow

### For Students:
1. Access the admission form (public page)
2. Fill in required information
3. Select desired course
4. Submit application
5. Receive confirmation email
6. Wait for admin review
7. Receive status update email (Selected/Rejected)

### For Administrators:
1. Login to admin dashboard
2. View "All Applications" report
3. Review applications
4. Update application status:
   - **SELECTED**: Student moves to "Selected Students" report, receives acceptance email
   - **REJECTED**: Provide rejection reason, student receives rejection email
5. View "Selected Students" report
6. Manage enrolled students

## Validation Rules

- **Email**: Valid email format, unique across applications
- **Phone Number**: Exactly 10 digits
- **Applicant Name**: Non-empty string
- **Course**: Must exist in database
- **Address**: Non-empty string
- **Rejection Reason**: Required when rejecting applications

## Error Handling

- Duplicate email prevention
- Invalid course validation
- Missing required field validation
- Proper HTTP status codes
- Meaningful error messages

## Future Enhancements

- User authentication & authorization
- Role-based access control (Student, Admin, Superadmin)
- File upload support (documents, photo)
- Advanced filtering and sorting
- Batch operations
- Payment gateway integration
- SMS notifications
- Interview scheduling
- Document verification workflow
- Multi-language support

## Troubleshooting

### H2 Database Issues:
- Ensure H2 is enabled in application.properties
- Access H2 console at `/h2-console`

### Email Not Sending:
- Check SMTP credentials in application.properties
- Ensure "Less secure app access" is enabled (for Gmail)
- Check logs for email errors

### Application Won't Start:
- Check port 8080 is not in use
- Verify Java 17+ is installed
- Check Maven installation

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit changes with clear messages
4. Push to branch
5. Create a pull request

## License

This project is licensed under the MIT License.

## Support

For issues, questions, or suggestions, please create an issue in the repository.

---

**Version**: 1.0.0  
**Last Updated**: December 2025  
**Author**: Development Team
